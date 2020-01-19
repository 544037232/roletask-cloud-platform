package com.refordom.app.config.filter;

import com.refordom.app.config.*;
import com.refordom.app.config.event.FilterSuccessEvent;
import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.config.handler.AppFailureHandler;
import com.refordom.app.config.handler.AppNullFailureHandler;
import com.refordom.app.config.handler.AppNullSuccessHandler;
import com.refordom.app.config.handler.AppSuccessHandler;
import com.refordom.app.config.util.RequestUtils;
import com.refordom.app.core.AppContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author pricess.wang
 * @date 2019/12/12 17:10
 */
@Slf4j
public class DefaultAppFilterChain implements AppFilterChain, ApplicationEventPublisherAware {

    private final AppRequestMatcher requestMatcher;

    private final List<Filter> filters;

    /**
     * 过滤器执行成功后继续执行过滤器
     */
    private boolean continueChainBeforeSuccessfulFilter;

    private final List<AppStoreProvider> storeProviders;

    private final String actionName;

    private final PlatformTransactionManager transactionManager;

    //设置事务的传播机制
    private final DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);

    private ApplicationEventPublisher eventPublisher;

    private AppSuccessHandler successHandler;

    private AppFailureHandler failureHandler;

    private final List<AppServiceProvider> serviceProviders;

    public DefaultAppFilterChain(String actionName,
                                 boolean continueChainBeforeSuccessfulFilter,
                                 PlatformTransactionManager transactionManager,
                                 List<AppStoreProvider> storeProviders,
                                 List<AppServiceProvider> serviceProviders,
                                 AppRequestMatcher requestMatcher,
                                 List<Filter> filters) {
        this.actionName = actionName;
        this.transactionManager = transactionManager;
        this.storeProviders = storeProviders;
        this.serviceProviders = serviceProviders;
        this.requestMatcher = requestMatcher;
        this.filters = new ArrayList<>(filters);
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
    }

    public AppRequestMatcher getRequestMatcher() {
        return requestMatcher;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public boolean matches(HttpServletRequest request) {
        return requestMatcher.matches(request);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) rep;

        VirtualFilterChain virtualFilterChain = new VirtualFilterChain(chain, filters, continueChainBeforeSuccessfulFilter);

        try {

            virtualFilterChain.doFilter(request, response);

            for (AppServiceProvider provider : serviceProviders) {

                if (provider.supports(provider.getClass())) {
                    provider.provider(null);
                }
            }

            if (!storeProviders.isEmpty()) {
                doTransaction();
            }

        } catch (AppContextException failed) {
            unsuccessfulFilter(request, response, failed);
            return;
        }

        successfulAuthentication(request, response, chain, null);
    }

    private void doTransaction() {
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);

        try {
            for (AppStoreProvider appStoreProvider : storeProviders) {

                AppContextHolder.getContext().getResult().stream()
                        .filter(t -> appStoreProvider.supports(t.getClass()))
                        .forEach(appStoreProvider::provider);
            }

            transactionManager.commit(transStatus);
        } catch (Exception e) {

            transactionManager.rollback(transStatus);
            throw e;
        }
    }

    @Override
    public List<AppServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public String getActionName() {
        return actionName;
    }

    private void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, AppToken appToken) throws IOException, ServletException {

        if (eventPublisher != null) {
            eventPublisher.publishEvent(new FilterSuccessEvent(appToken));
        }

        successHandler.onSuccessContext(request, response, appToken);
    }

    private void unsuccessfulFilter(HttpServletRequest request, HttpServletResponse response, AppContextException failed) throws IOException, ServletException {
        failureHandler.onFailureContext(request, response, failed);
    }

    public void setSuccessHandler(AppSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setFailureHandler(AppFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public List<AppStoreProvider> getStoreProviders() {
        return storeProviders;
    }

    @Override
    public String toString() {
        return "[ " + requestMatcher + ", " + filters + "]";
    }

    private static class VirtualFilterChain implements FilterChain {

        private final int size;

        private int currentPosition = 0;

        private final List<Filter> filters;

        private final boolean continueChainBeforeSuccessfulFilter;

        private FilterChain originChain;

        public VirtualFilterChain(FilterChain chain,
                                  List<Filter> filters,
                                  boolean continueChainBeforeSuccessfulFilter) {
            this.originChain = chain;
            this.size = filters.size();
            this.filters = filters;
            this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
        }


        @Override
        public void doFilter(ServletRequest req, ServletResponse response) throws IOException, ServletException {
            if (currentPosition == size) {

                if (!continueChainBeforeSuccessfulFilter) {
                    return;
                }

                originChain.doFilter(req, response);
            } else {
                currentPosition++;

                Filter nextFilter = filters.get(currentPosition - 1);

                if (log.isDebugEnabled()) {
                    HttpServletRequest request = (HttpServletRequest) req;
                    log.debug(RequestUtils.buildRequestUrl(request)
                            + " at position " + currentPosition + " of " + size
                            + " in additional filter chain; firing Filter: '"
                            + nextFilter.getClass().getSimpleName() + "'");
                }

                nextFilter.doFilter(req, response, this);

            }
        }
    }

}
