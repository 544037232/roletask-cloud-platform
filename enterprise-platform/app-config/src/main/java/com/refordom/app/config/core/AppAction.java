package com.refordom.app.config.core;

import com.refordom.app.config.AppActionBuilder;
import com.refordom.app.config.AppActionMatcher;
import com.refordom.app.config.AppRequestMatcher;
import com.refordom.app.config.configurer.ActionParamsCheckConfigurer;
import com.refordom.app.config.configurer.AuthenticationConfigurer;
import com.refordom.app.config.filter.DefaultAppFilterChain;
import com.refordom.common.builder.AbstractConfiguredObjectBuilder;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectPostProcessor;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:31
 */
public class AppAction extends AbstractConfiguredObjectBuilder<DefaultAppFilterChain, AppAction>
        implements ObjectBuilder<DefaultAppFilterChain>, AppActionBuilder<AppAction> {

    private AppRequestMatcher requestMatcher;

    private List<Filter> filters = new ArrayList<>();

    public AppAction(ObjectPostProcessor<Object> objectPostProcessor) {
        super(objectPostProcessor);
    }

    public ActionParamsCheckConfigurer<AppAction> paramsCheck() throws Exception {
        return getOrApply(new ActionParamsCheckConfigurer<>());
    }


    public AuthenticationConfigurer<AppAction> auth() throws Exception {
        return getOrApply(new AuthenticationConfigurer<>());
    }

    @Override
    protected DefaultAppFilterChain performBuild() throws Exception {
        return new DefaultAppFilterChain(requestMatcher, filters);
    }

    public AppAction actionRequestMatcher(String action) {
        this.requestMatcher = new AppActionMatcher(action);
        return this;
    }

    @Override
    public AppAction addFilter(Filter filter) {
        this.filters.add(filter);
        return this;
    }
}
