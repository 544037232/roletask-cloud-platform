package com.omc.builder.configurer;

import com.omc.builder.global.future.FutureBuilder;
import com.omc.builder.global.future.FutureManager;
import com.omc.object.ObjectConfigurerAdapter;

public abstract class AbstractManagerConfigurer<T extends AbstractManagerConfigurer<T, B>, B extends FutureBuilder<B>>
        extends ObjectConfigurerAdapter<FutureManager, B> {
}
