package com.omc.builder.global;

import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectConfigurer;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public interface FutureConfigurer<T extends ObjectBuilder<GlobalManager>> extends
        ObjectConfigurer<GlobalManager, T> {

}
