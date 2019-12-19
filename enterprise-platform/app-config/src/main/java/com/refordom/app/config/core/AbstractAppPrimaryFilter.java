package com.refordom.app.config.core;

import com.refordom.app.config.AppDetails;
import com.refordom.app.config.AppProvider;
import com.refordom.app.config.event.FilterSuccessEvent;
import com.refordom.app.config.exception.AppContextException;
import com.refordom.app.config.handler.AppFailureHandler;
import com.refordom.app.config.handler.AppSuccessHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 主要的上下文过滤器，此过滤器包含了整个上下文处理和异常捕获，同样包括事件监听，所有应用执行接口只能包含一个主过滤器
 *
 * @author pricess.wang
 * @date 2019/12/16 18:28
 */
public abstract class AbstractAppPrimaryFilter implements Filter, ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    /**
     * 过滤器执行成功后继续执行过滤器
     */
    private boolean continueChainBeforeSuccessfulFilter = false;

    private AppSuccessHandler successHandler;

    private AppFailureHandler failureHandler;

    private List<AppProvider> providers = Collections.emptyList();

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) rep;

        AppDetails appDetails;

        try {
            appDetails = onContext(request, response);

            for (AppProvider provider : providers) {

                if (provider.supports(appDetails.getClass())) {
                    provider.provider(appDetails);
                }
            }

            if (appDetails == null) {
                return;
            }
        } catch (AppContextException failed) {
            unsuccessfulFilter(request, response, failed);
            return;
        }

        if (continueChainBeforeSuccessfulFilter) {
            chain.doFilter(request, response);
        }

        successfulAuthentication(request, response, chain, appDetails);
    }

    private void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, AppDetails appDetails) throws IOException, ServletException {

        if (eventPublisher != null) {
            eventPublisher.publishEvent(new FilterSuccessEvent(appDetails));
        }

        successHandler.onSuccessContext(request, response, appDetails);
    }

    private void unsuccessfulFilter(HttpServletRequest request, HttpServletResponse response, AppContextException failed) throws IOException, ServletException {
        failureHandler.onFailureContext(request, response, failed);
    }

    protected abstract AppDetails onContext(HttpServletRequest request, HttpServletResponse response);

    public void setContinueChainBeforeSuccessfulFilter(boolean continueChainBeforeSuccessfulFilter) {
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
    }

    public void setSuccessHandler(AppSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setFailureHandler(AppFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public void setProviders(List<AppProvider> providers) {
        this.providers = providers;
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
