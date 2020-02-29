package com.refordom.common.action.builder.core;

import com.refordom.common.action.builder.*;
import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.event.FilterSuccessEvent;
import com.refordom.common.action.builder.exception.AppContextException;
import com.refordom.common.action.builder.handler.ActionFailureHandler;
import com.refordom.common.action.builder.handler.ActionSuccessHandler;
import com.refordom.common.action.builder.util.RequestUtils;
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
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/12 17:10
 */
@Slf4j
public class DefaultActionFilterChain implements ActionFilterChain, ApplicationEventPublisherAware {

    private ActionRequestMatcher requestMatcher;

    private final List<Filter> filters;

    /**
     * 过滤器执行成功后继续执行过滤器
     */
    private boolean continueChainBeforeSuccessfulFilter;

    private List<ActionStoreProvider> storeProviders;

    private String actionName;

    private PlatformTransactionManager transactionManager;

    //设置事务的传播机制
    private final DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);

    private ApplicationEventPublisher eventPublisher;

    private ActionSuccessHandler successHandler;

    private ActionFailureHandler failureHandler;

    private List<ActionServiceProvider> serviceProviders;

    private Class<? extends ResultToken> resultTokenClass;

    public DefaultActionFilterChain(List<Filter> filters) {
        this.filters = filters;
    }

    public ActionRequestMatcher getRequestMatcher() {
        return requestMatcher;
    }

    @Override
    public List<Filter> getFilters() {
        return filters;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return requestMatcher.matches(request);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) rep;

        VirtualFilterChain virtualFilterChain = new VirtualFilterChain(chain, filters, continueChainBeforeSuccessfulFilter);

        ResultToken resultToken = null;

        try {

            virtualFilterChain.doFilter(request, response);

            if (resultTokenClass != null) {

                resultToken = createResultTokenInstance();

                for (ActionServiceProvider provider : serviceProviders) {

                    if (provider.supports(resultToken.getClass())) {
                        provider.provider(resultToken);
                    }
                }

            }

            if (!storeProviders.isEmpty()) {
                doTransaction();
            }
        } catch (AppContextException failed) {
            unsuccessfulFilter(request, response, failed);
            return;
        }

        successfulAuthentication(request, response, chain, resultToken);
    }

    private ResultToken createResultTokenInstance() {
        try {
            return resultTokenClass.newInstance();
        } catch (Exception e) {
            return new ResultToken() {};
        }
    }

    private void doTransaction() {

        if (transactionManager == null) {
            store();
            return;
        }

        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);

        try {
            store();

            transactionManager.commit(transStatus);
        } catch (Exception e) {

            transactionManager.rollback(transStatus);
            throw e;
        }
    }

    private void store() {

        for (ActionStoreProvider appStoreProvider : storeProviders) {

            ActionContextHolder.getContext().getResult().stream()
                    .filter(t -> appStoreProvider.supports(t.getClass()))
                    .forEach(appStoreProvider::provider);
        }
    }

    @Override
    public List<ActionServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setResultTokenClass(Class<? extends ResultToken> resultTokenClass) {
        this.resultTokenClass = resultTokenClass;
    }

    public String getActionName() {
        return actionName;
    }

    private void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, ResultToken resultToken) throws IOException, ServletException {

        if (eventPublisher != null) {
            eventPublisher.publishEvent(new FilterSuccessEvent(resultToken));
        }

        if (successHandler != null) {
            successHandler.onSuccessContext(request, response, resultToken);
        }
    }

    private void unsuccessfulFilter(HttpServletRequest request, HttpServletResponse response, AppContextException failed) throws IOException, ServletException {

        if (failureHandler != null) {
            failureHandler.onFailureContext(request, response, failed);
        }
    }

    public void setSuccessHandler(ActionSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setFailureHandler(ActionFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public void setContinueChainBeforeSuccessfulFilter(boolean continueChainBeforeSuccessfulFilter) {
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setRequestMatcher(ActionRequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setStoreProviders(List<ActionStoreProvider> storeProviders) {
        this.storeProviders = storeProviders;
    }

    public void setServiceProviders(List<ActionServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public List<ActionStoreProvider> getStoreProviders() {
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
