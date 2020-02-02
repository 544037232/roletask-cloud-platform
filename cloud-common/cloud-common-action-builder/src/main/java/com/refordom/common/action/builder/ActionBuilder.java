package com.refordom.common.action.builder;

import com.refordom.common.action.builder.core.DefaultActionFilterChain;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectConfigurer;

import javax.servlet.Filter;

/**
 * 应用请求构建接口
 *
 * @author pricess.wang
 * @date 2019/12/13 18:48
 */
public interface ActionBuilder<H extends ActionBuilder<H>> extends
        ObjectBuilder<DefaultActionFilterChain> {

    <C extends ObjectConfigurer<DefaultActionFilterChain, H>> C getConfigurer(
            Class<C> clazz);

    <C extends ObjectConfigurer<DefaultActionFilterChain, H>> C removeConfigurer(
            Class<C> clazz);

    <C> void setSharedObject(Class<C> sharedType, C object);

    <C> C getSharedObject(Class<C> sharedType);

    H addFilter(Filter filter,Integer sort);

    H addServiceProvider(ActionServiceProvider appProvider);

    H addStoreProvider(ActionStoreProvider appProvider);
}
