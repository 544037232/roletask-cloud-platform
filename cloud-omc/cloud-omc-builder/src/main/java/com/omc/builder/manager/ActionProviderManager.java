package com.omc.builder.manager;

import com.omc.builder.ActionMatcher;
import com.omc.builder.ResultToken;
import com.omc.builder.api.ProviderManager;
import com.omc.builder.api.ServiceManager;
import com.omc.builder.api.StoreManager;
import com.omc.builder.event.SuccessEvent;
import com.omc.builder.filter.VirtualFilterChain;
import com.omc.builder.handler.FailureHandler;
import com.omc.builder.handler.SuccessHandler;
import org.springframework.context.ApplicationEventPublisher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActionProviderManager implements ProviderManager {

    private List<Filter> filters;

    private ActionMatcher actionMatcher;

    private ServiceManager serviceManager;

    private StoreManager storeManager;

    private ApplicationEventPublisher eventPublisher;

    private SuccessHandler successHandler;

    private FailureHandler failureHandler;

    /**
     * 过滤器执行成功后继续执行过滤器
     */
    private boolean continueChainBeforeSuccessfulFilter;

    public void setSuccessHandler(SuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setFailureHandler(FailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public void setActionMatcher(ActionMatcher actionMatcher) {
        this.actionMatcher = actionMatcher;
    }

    @Override
    public List<Filter> getFilters() {
        return filters;
    }

    @Override
    public ActionMatcher getActionMatcher() {
        return actionMatcher;
    }

    public void setContinueChainBeforeSuccessfulFilter(boolean continueChainBeforeSuccessfulFilter) {
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
    }

    @Override
    public void attemptExecutor(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {

        VirtualFilterChain virtualFilterChain = new VirtualFilterChain(filterChain, filters, continueChainBeforeSuccessfulFilter);

        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) rep;

        ResultToken resultToken;

        try {

            virtualFilterChain.doFilter(req, rep);

            if (!virtualFilterChain.hasFinished()) {
                return;
            }

            resultToken = serviceManager.attemptExecutor(request, response);

            if (resultToken != null) {
                storeManager.attemptExecutor(resultToken);
            }

        } catch (Exception e) {
            unsuccessfulExecutor(request, response, e);
            return;
        }

        if (continueChainBeforeSuccessfulFilter) {
            filterChain.doFilter(req, rep);
        }

        successfulExecutor(request, response, resultToken);
    }

    private void successfulExecutor(HttpServletRequest request, HttpServletResponse response, ResultToken resultToken) throws IOException, ServletException {

        if (eventPublisher != null) {
            eventPublisher.publishEvent(new SuccessEvent(resultToken));
        }

        if (successHandler != null) {
            successHandler.onSuccessContext(request, response, resultToken);
        }
    }

    private void unsuccessfulExecutor(HttpServletRequest request, HttpServletResponse response, Exception failed) throws IOException, ServletException {

        if (failureHandler != null) {
            failureHandler.onFailureContext(request, response, failed);
        }
    }

    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
}
