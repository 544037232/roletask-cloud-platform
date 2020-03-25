package com.omc.builder.configurer;


import com.omc.builder.ActionBuilder;
import com.omc.builder.api.ProviderManager;
import com.omc.object.ObjectConfigurerAdapter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:47
 */
public class AbstractActionConfigurer<T extends AbstractActionConfigurer<T, B>, B extends ActionBuilder<B>>
        extends ObjectConfigurerAdapter<ProviderManager, B> {

}
