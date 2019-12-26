package com.refordom.app.config.core;

import com.refordom.app.config.AppActionBuilder;
import com.refordom.app.config.AppActionMatcher;
import com.refordom.app.config.AppProvider;
import com.refordom.app.config.AppRequestMatcher;
import com.refordom.app.config.configurer.ActionParamsCheckConfigurer;
import com.refordom.app.config.filter.DefaultAppFilterChain;
import com.refordom.app.config.filter.StandardAppPrimaryFilter;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import com.refordom.common.builder.exception.ObjectBuiltException;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:31
 */
public class AppAction extends AbstractConfiguredObjectBuilder<DefaultAppFilterChain, AppAction>
        implements ObjectBuilder<DefaultAppFilterChain>, AppActionBuilder<AppAction> {

    private AppRequestMatcher requestMatcher;

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

    public ActionParamsCheckConfigurer<AppAction> paramsCheck() throws Exception {
        return getOrApply(new ActionParamsCheckConfigurer<>());
    }

    @Override
    protected DefaultAppFilterChain performBuild() throws Exception {
        if (requestMatcher == null) {
            throw new ObjectBuiltException("this requestMatcher must bu appoint");
        }
        filters.add(primaryFilter);
        primaryFilter.setProviders(providers);

        return new DefaultAppFilterChain(continueChainBeforeSuccessfulFilter,requestMatcher, filters);
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
            this.primaryFilter = (AbstractAppPrimaryFilter) filter;
        } else {
            this.filters.add(filter);
        }
        return this;
    }
}
