package com.refordom.app.config.core;

import com.refordom.app.config.*;
import com.refordom.app.config.configurer.ActionParamsCheckConfigurer;
import com.refordom.app.config.exception.DeprecatedException;
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

    private AppRequestMatcher requestMatcher = new NullActionMatched();

    private List<Filter> filters = new ArrayList<>();

    private List<AppProvider> providers = new ArrayList<>();

    private AbstractAppPrimaryFilter primaryFilter = new StandardAppPrimaryFilter();

    private boolean continueChainBeforeSuccessfulFilter = false;

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
        primaryFilter.setProviders(providers);

        return new DefaultAppFilterChain(continueChainBeforeSuccessfulFilter, requestMatcher, filters);
    }

    public AppAction actionRequestMatcher(String action) {
        this.requestMatcher = new AppActionMatcher(action);
        return this;
    }

    @Override
    public AppAction addProvider(AppProvider appProvider) {
        this.providers.add(appProvider);
        return this;
    }

    public void continueChainBeforeSuccessfulFilter(boolean continueChainBeforeSuccessfulFilter) {
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
    }

    @Override
    public AppAction addFilter(Filter filter) {
        if (filter instanceof AbstractAppPrimaryFilter) {
            throw new DeprecatedException("this primary is readonly");
        }
        this.filters.add(filter);
        return this;
    }
}
