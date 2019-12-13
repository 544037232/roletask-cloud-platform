package com.refordom.app.config.configurer;

import com.refordom.app.config.AppActionBuilder;
import com.refordom.app.config.filter.DefaultAppFilterChain;
import com.refordom.common.builder.ObjectConfigurerAdapter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:47
 */
public class AbstractAppActionConfigurer <T extends AbstractAppActionConfigurer<T, B>, B extends AppActionBuilder<B>>
        extends ObjectConfigurerAdapter<DefaultAppFilterChain, B> {
}
