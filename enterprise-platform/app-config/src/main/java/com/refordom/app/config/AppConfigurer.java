package com.refordom.app.config;

import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectConfigurer;

import javax.servlet.Filter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public interface AppConfigurer<T extends ObjectBuilder<Filter>> extends
        ObjectConfigurer<Filter, T> {

}
