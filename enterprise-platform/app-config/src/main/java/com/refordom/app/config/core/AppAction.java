package com.refordom.app.config.core;

import com.refordom.app.config.*;
import com.refordom.app.config.configurer.ActionParamsCheckConfigurer;
import com.refordom.app.config.filter.DefaultAppFilterChain;
import com.refordom.app.config.filter.StandardAppPrimaryFilter;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import lombok.extern.slf4j.Slf4j;

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

    private AbstractAppPrimaryFilter primaryFilter = new StandardAppPrimaryFilter();

    private boolean continueChainBeforeSuccessfulFilter = false;

    private FilterComparator comparator = new FilterComparator();

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
        filters.add(primaryFilter);
        primaryFilter.setProviders(serviceProviders);

        filters.sort(comparator);

        return new DefaultAppFilterChain(
                actionName,
                continueChainBeforeSuccessfulFilter,
                storeProviders,
                requestMatcher,
                filters);
    }

    public AppAction actionRequestMatcher(String action) {
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

    @Override
    public AppAction addStoreProvider(AppStoreProvider appStoreProvider) {
        this.storeProviders.add(appStoreProvider);
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

    @Override
    public AppAction addFilter(Filter filter) {
        Class<? extends Filter> filterClass = filter.getClass();
        if (!comparator.isRegistered(filterClass) && !(filter instanceof AbstractAppPrimaryFilter)) {
            throw new IllegalArgumentException(
                    "The Filter class "
                            + filterClass.getName()
                            + " does not have a registered order and cannot be added without a specified order. Consider using addFilterBefore or addFilterAfter instead.");
        }

        if (filter instanceof AbstractAppPrimaryFilter) {
            this.primaryFilter = (AbstractAppPrimaryFilter) filter;
        } else {
            this.filters.add(filter);
        }
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
