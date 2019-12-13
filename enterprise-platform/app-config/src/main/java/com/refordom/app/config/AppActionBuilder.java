package com.refordom.app.config;

import com.refordom.app.config.filter.DefaultAppFilterChain;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectConfigurer;

import javax.servlet.Filter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:48
 */
public interface AppActionBuilder<H extends AppActionBuilder<H>> extends
        ObjectBuilder<DefaultAppFilterChain> {

    <C extends ObjectConfigurer<DefaultAppFilterChain, H>> C getConfigurer(
            Class<C> clazz);

    <C extends ObjectConfigurer<DefaultAppFilterChain, H>> C removeConfigurer(
            Class<C> clazz);

    <C> void setSharedObject(Class<C> sharedType, C object);

    <C> C getSharedObject(Class<C> sharedType);

    H addFilter(Filter filter);

}
