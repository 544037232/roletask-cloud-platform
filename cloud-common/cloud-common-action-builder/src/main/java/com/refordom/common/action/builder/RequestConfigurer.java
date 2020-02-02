package com.refordom.common.action.builder;

import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectConfigurer;

import javax.servlet.Filter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public interface RequestConfigurer<T extends ObjectBuilder<Filter>> extends
        ObjectConfigurer<Filter, T> {

}
