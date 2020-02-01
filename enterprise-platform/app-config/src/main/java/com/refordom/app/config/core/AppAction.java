package com.refordom.app.config.core;

import com.refordom.app.config.*;
import com.refordom.app.config.configurer.ActionParamsCheckConfigurer;
import com.refordom.app.config.filter.DefaultAppFilterChain;
import com.refordom.app.config.handler.AppFailureHandler;
import com.refordom.app.config.handler.AppNullFailureHandler;
import com.refordom.app.config.handler.AppNullSuccessHandler;
import com.refordom.app.config.handler.AppSuccessHandler;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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
public class AppAction extends AbstractConfiguredObjectBuilder<DefaultAppFilterChain, AppAction>
        implements ObjectBuilder<DefaultAppFilterChain>, AppActionBuilder<AppAction> {

    private String actionName;

    private AppRequestMatcher requestMatcher = new NullActionMatched();

    private List<Filter> filters = new ArrayList<>();

    private List<AppServiceProvider> serviceProviders = new ArrayList<>();

    private List<AppStoreProvider> storeProviders = new ArrayList<>();

    private boolean continueChainBeforeSuccessfulFilter = false;

    private FilterComparator comparator = new FilterComparator();

    private AppSuccessHandler successHandler = new AppNullSuccessHandler();

    private AppFailureHandler failureHandler = new AppNullFailureHandler();

    private ApplicationEventPublisher applicationEventPublisher;

    private Class<? extends AppToken> resultTokenClass;

    @SuppressWarnings("unchecked")
    public AppAction(ObjectPostProcessor<Object> objectPostProcessor, Map<Class<?>, Object> sharedObjects) {
        super(objectPostProcessor);
        for (Map.Entry<Class<?>, Object> entry : sharedObjects.entrySet()) {
            setSharedObject((Class<Object>) entry.getKey(), entry.getValue());
        }
    }

    /**
     * 参数校验
     * 默认的参数校验包括app_id,app_type两个参数的必填，其他需自行配置
     */
    public ActionParamsCheckConfigurer<AppAction> paramsCheck() throws Exception {
        return getOrApply(new ActionParamsCheckConfigurer<>());
    }

    @Override
    protected DefaultAppFilterChain performBuild() throws Exception {
        if (requestMatcher instanceof NullActionMatched) {
            log.warn("this appAction requestMatcher is null matched");
        }

        filters.sort(comparator);

        DefaultAppFilterChain appFilterChain = new DefaultAppFilterChain(
                actionName,
                continueChainBeforeSuccessfulFilter,
                this.getSharedObject(PlatformTransactionManager.class),
                storeProviders,
                serviceProviders,
                requestMatcher,
                filters);

        appFilterChain.setSuccessHandler(successHandler);
        appFilterChain.setFailureHandler(failureHandler);
        appFilterChain.setResultTokenClass(resultTokenClass);
        appFilterChain.setApplicationEventPublisher(applicationEventPublisher);

        return appFilterChain;
    }

    public AppAction actionRequestMatcher(String action) {
        if (StringUtils.isEmpty(action)) {
            throw new IllegalArgumentException("the action can not be null");
        }

        if (actionName == null) {
            this.actionName = action;
        }
        this.requestMatcher = new AppActionMatcher(action);
        return this;
    }

    @Override
    public AppAction addServiceProvider(AppServiceProvider appProvider) {
        this.serviceProviders.add(appProvider);
        return this;
    }

    public AppAction successHandler(AppSuccessHandler successHandler) {
        this.successHandler = successHandler;
        return this;
    }

    public AppAction failureHandler(AppFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        return this;
    }

    @Override
    public AppAction addStoreProvider(AppStoreProvider appStoreProvider) {
        this.storeProviders.add(appStoreProvider);
        return this;
    }

    public AppAction resultToken(Class<? extends AppToken> tokenClass) {
        this.resultTokenClass = tokenClass;
        return this;
    }

    public AppAction actionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    public AppAction continueChainBeforeSuccessfulFilter(boolean continueChainBeforeSuccessfulFilter) {
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
        return this;
    }

    public AppAction applicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        return this;
    }

    @Override
    public AppAction addFilter(Filter filter) {
        Class<? extends Filter> filterClass = filter.getClass();
        if (!comparator.isRegistered(filterClass)) {
            throw new IllegalArgumentException(
                    "The Filter class "
                            + filterClass.getName()
                            + " does not have a registered order and cannot be added without a specified order. Consider using addFilterBefore or addFilterAfter instead.");
        }

        filters.add(filter);
        return this;
    }

    @Override
    public AppAction addFilterAfter(Filter filter, Class<? extends Filter> afterFilter) {
        comparator.registerAfter(filter.getClass(), afterFilter);
        return addFilter(filter);
    }

    @Override
    public AppAction addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter) {
        comparator.registerBefore(filter.getClass(), beforeFilter);
        return addFilter(filter);
    }
}
