package com.omc.builder;

import com.omc.builder.api.ProviderManager;
import com.omc.builder.api.ServiceManager;
import com.omc.builder.api.StoreManager;
import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectConfigurer;

import javax.servlet.Filter;

/**
 * 应用请求构建接口
 *
 * @author pricess.wang
 * @date 2019/12/13 18:48
 */
public interface ActionBuilder<H extends ActionBuilder<H>> extends
        ObjectBuilder<ProviderManager> {

    <C extends ObjectConfigurer<ProviderManager, H>> C getConfigurer(
            Class<C> clazz);

    <C extends ObjectConfigurer<ProviderManager, H>> C removeConfigurer(
            Class<C> clazz);

    <C> void setSharedObject(Class<C> sharedType, C object);

    <C> C getSharedObject(Class<C> sharedType);

    H addFilter(Filter filter, Integer sort);

    H actionMatcher(ActionMatcher actionMatcher);

    H serviceManager(ServiceManager serviceManager);

    H storeManager(StoreManager storeManager);
}
