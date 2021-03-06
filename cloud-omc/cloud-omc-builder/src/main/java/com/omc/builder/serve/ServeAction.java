package com.omc.builder.serve;

import com.omc.builder.ActionBuilder;
import com.omc.builder.ActionMatcher;
import com.omc.builder.api.ProviderManager;
import com.omc.builder.configurer.ParamsCheckConfigurer;
import com.omc.builder.configurer.RequestMatcherConfigurer;
import com.omc.builder.configurer.ServiceProviderConfigurer;
import com.omc.builder.configurer.StoreProviderConfigurer;
import com.omc.builder.filter.DebugFilter;
import com.omc.builder.filter.FilterComparator;
import com.omc.builder.handler.FailureHandler;
import com.omc.builder.handler.NullFailureHandler;
import com.omc.builder.handler.NullSuccessHandler;
import com.omc.builder.handler.SuccessHandler;
import com.omc.builder.manager.ActionProviderManager;
import com.omc.object.AbstractConfiguredObjectBuilder;
import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 请求构建对象
 *
 * @author pricess.wang
 * @date 2019/12/13 18:31
 */
@Slf4j
public class ServeAction extends AbstractConfiguredObjectBuilder<ProviderManager, ServeAction>
        implements ObjectBuilder<ProviderManager>, ActionBuilder<ServeAction> {

    private List<Filter> filters = new ArrayList<>();

    private SuccessHandler successHandler = new NullSuccessHandler();

    private FailureHandler failureHandler = new NullFailureHandler();

    private FilterComparator comparator = new FilterComparator();

    private boolean debugEnabled;

    private ActionMatcher actionMatcher;

    private boolean continueChainBeforeSuccessfulFilter = false;

    @SuppressWarnings("unchecked")
    public ServeAction(ObjectPostProcessor<Object> objectPostProcessor, Map<Class<?>, Object> sharedObjects) {

        super(objectPostProcessor);

        for (Map.Entry<Class<?>, Object> entry : sharedObjects.entrySet()) {
            setSharedObject((Class<Object>) entry.getKey(), entry.getValue());
        }

    }

    @Override
    public ServeAction addFilterAfter(Filter filter, Class<? extends Filter> afterFilter) {
        comparator.registerAfter(filter.getClass(), afterFilter);
        return addFilter(filter);
    }

    @Override
    public ServeAction addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter) {
        comparator.registerBefore(filter.getClass(), beforeFilter);
        return addFilter(filter);
    }

    @Override
    public ServeAction addFilter(Filter filter) {
        Class<? extends Filter> filterClass = filter.getClass();
        if (!comparator.isRegistered(filterClass)) {
            throw new IllegalArgumentException(
                    "The Filter class "
                            + filterClass.getName()
                            + " does not have a registered order and cannot be added without a specified order. Consider using addFilterBefore or addFilterAfter instead.");
        }
        this.filters.add(filter);
        return this;
    }

    @Override
    public ServeAction actionMatcher(ActionMatcher actionMatcher) {
        this.actionMatcher = actionMatcher;
        return this;
    }

    @Override
    protected ProviderManager performBuild() throws Exception {

        ActionProviderManager actionProviderManager = new ActionProviderManager();
        actionProviderManager.setActionMatcher(actionMatcher);

        if (debugEnabled) {
            addFilter(new DebugFilter(actionProviderManager));
            log.warn("********** action:" + actionMatcher.getActionName() + " debugging is enabled.***********");
        }

        filters.sort(comparator);

        actionProviderManager.setFilters(filters);
        actionProviderManager.setSuccessHandler(successHandler);
        actionProviderManager.setFailureHandler(failureHandler);
        actionProviderManager.setContinueChainBeforeSuccessfulFilter(continueChainBeforeSuccessfulFilter);

        ApplicationEventPublisher eventPublisher = getSharedObject(ApplicationEventPublisher.class);
        if (eventPublisher != null) {
            actionProviderManager.setEventPublisher(eventPublisher);
        }

        return actionProviderManager;
    }

    /**
     * 参数校验
     */
    public ParamsCheckConfigurer<ServeAction> paramsCheck() throws Exception {
        return getOrApply(new ParamsCheckConfigurer<>());
    }

    /**
     * 请求方法和URL
     */
    public RequestMatcherConfigurer<ServeAction> requestMatcher() throws Exception {
        return getOrApply(new RequestMatcherConfigurer<>());
    }

    /**
     * service服务相关设置
     */
    public ServiceProviderConfigurer<ServeAction> service() throws Exception {
        return getOrApply(new ServiceProviderConfigurer<>());
    }

    /**
     * 存储配置，包括持久化执行，事务管理
     */
    public StoreProviderConfigurer<ServeAction> store() throws Exception {
        return getOrApply(new StoreProviderConfigurer<>(this.getSharedObject(ApplicationContext.class)));
    }

    public ServeAction successHandler(SuccessHandler successHandler) {
        this.successHandler = successHandler;
        return this;
    }

    public ServeAction failureHandler(FailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        return this;
    }

    public ServeAction continueChainBeforeSuccessfulFilter(boolean continueChainBeforeSuccessfulFilter) {
        this.continueChainBeforeSuccessfulFilter = continueChainBeforeSuccessfulFilter;
        return this;
    }

    public ServeAction debug(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        return this;
    }

}
