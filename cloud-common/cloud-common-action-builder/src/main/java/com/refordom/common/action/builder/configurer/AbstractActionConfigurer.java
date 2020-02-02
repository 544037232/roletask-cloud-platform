package com.refordom.common.action.builder.configurer;

import com.refordom.common.action.builder.ActionBuilder;
import com.refordom.common.action.builder.core.DefaultActionFilterChain;
import com.refordom.common.builder.ObjectConfigurerAdapter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:47
 */
public class AbstractActionConfigurer <T extends AbstractActionConfigurer<T, B>, B extends ActionBuilder<B>>
        extends ObjectConfigurerAdapter<DefaultActionFilterChain, B> {
}
