package com.refordom.common.action.builder.core;

import com.refordom.common.action.builder.*;
import com.refordom.common.action.builder.configurer.ActionParamsCheckConfigurer;
import com.refordom.common.action.builder.handler.ActionFailureHandler;
import com.refordom.common.action.builder.handler.ActionNullFailureHandler;
import com.refordom.common.action.builder.handler.ActionNullSuccessHandler;
import com.refordom.common.action.builder.handler.ActionSuccessHandler;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:31
 */
@Slf4j
public class ServeAction extends AbstractConfiguredObjectBuilder<DefaultActionFilterChain, ServeAction>
        implements ObjectBuilder<DefaultActionFilterChain>, ActionBuilder<ServeAction> {

    private String actionName;

    private ActionRequestMatcher requestMatcher = new NullActionMatched();

    private List<Filter> filters = new ArrayList<>();

    private List<ActionServiceProvider> serviceProviders = new ArrayList<>();

    private List<ActionStoreProvider> storeProviders = new ArrayList<>();

    private boolean continueChainBeforeSuccessfulFilter = false;

    private ActionSuccessHandler successHandler = new ActionNullSuccessHandler();

    private ActionFailureHandler failureHandler = new ActionNullFailureHandler();

    private FilterComparator filterComparator = new FilterComparator();

    private ApplicationEventPublisher applicationEventPublisher;

    private Class<? extends ResultToken> resultTokenClass;

    @SuppressWarnings("unchecked")
    public ServeAction(ObjectPostProcessor<Object> objectPostProcessor, Map<Class<?>, Object> sharedObjects) {
        super(objectPostProcessor);
        for (Map.Entry<Class<?>, Object> entry : sharedObjects.entrySet()) {
            setSharedObject((Class<Object>) entry.getKey(), entry.getValue());
        }
    }

    /**
     * 参数校验
     */
    public ActionParamsCheckConfigurer<ServeAction> paramsCheck() throws Exception {
        return getOrApply(new ActionParamsCheckConfigurer<>());
    }

    @Override
    protected DefaultActionFilterChain performBuild() throws Exception {
        if (requestMatcher instanceof NullActionMatched) {
            log.warn("this appAction requestMatcher is null matched");
        }

        filters.sort(filterComparator);

        DefaultActionFilterChain actionFilterChain = new DefaultActionFilterChain(
                actionName,
                continueChainBeforeSuccessfulFilter,
                this.getSharedObject(PlatformTransactionManager.class),
                storeProviders,
                serviceProviders,
                requestMatcher,
                filters);

        actionFilterChain.setSuccessHandler(successHandler);
        actionFilterChain.setFailureHandler(failureHandler);
        actionFilterChain.setResultTokenClass(resultTokenClass);
        actionFilterChain.setApplicationEventPublisher(applicationEventPublisher);

        return actionFilterChain;
    }

    public ServeAction actionRequestMatcher(String action) {
        return actionRequestMatcher(action, HttpMethod.POST);
    }

    public ServeAction actionRequestMatcher(String action, HttpMethod method) {
        if (StringUtils.isEmpty(action)) {
            throw new IllegalArgumentException("the action can not be null");
        }

        if (StringUtils.isEmpty(method)) {
            throw new IllegalArgumentException("the action must specify a method");
        }

        if (actionName == null) {
            this.actionName = action;
        }
        this.requestMatcher = new ActionPatternMatcher(action, method);
        return this;
    }

    @Override
    public ServeAction addServiceProvider(ActionServiceProvider appProvider) {
        this.serviceProviders.add(appProvider);
        return this;
    }

    public ServeAction successHandler(ActionSuccessHandler successHandler) {
        this.successHandler = successHandler;
        return this;
    }

    public ServeAction failureHandler(ActionFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        return this;
    }

    @Override
    public ServeAction addStoreProvider(ActionStoreProvider appStoreProvider) {
        this.storeProviders.add(appStoreProvider);
        return this;
    }

    public ServeAction resultToken(Class<? extends ResultToken> tokenClass) {
        this.resultTokenClass = tokenClass;
        return this;
    }

    public ServeAction actionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    public ServeAction continueChainBeforeSuccessfulFilter(boolean continueChainBeforeSuccessfulFilter) {
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
        return this;
    }

    public ServeAction applicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        return this;
    }

    @Override
    public ServeAction addFilter(Filter filter, Integer sort) {
        filterComparator.register(filter, sort);
        filters.add(filter);
        return this;
    }

    public ServeAction sortReset(Class<? extends Filter> filter, Integer sort) {
        filterComparator.reset(filter, sort);
        return this;
    }
}
