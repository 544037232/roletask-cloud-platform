package com.omc.builder;

import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectConfigurer;

import javax.servlet.Filter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public interface RequestConfigurer<T extends ObjectBuilder<Filter>> extends
        ObjectConfigurer<Filter, T> {

}
