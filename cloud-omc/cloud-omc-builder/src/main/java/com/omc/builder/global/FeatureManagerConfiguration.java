package com.omc.builder.global;

import com.omc.builder.global.builder.GlobalManagerBuilder;
import com.omc.object.ObjectPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;

public class FeatureManagerConfiguration {

    @Bean
    public GlobalManager actionManagerBuilder(ObjectPostProcessor<Object> objectPostProcessor,
                                              List<FeatureConfigurerAdapter> configurers) throws Exception {
        configurers.sort(AnnotationAwareOrderComparator.INSTANCE);

        GlobalManagerBuilder actionManagerBuilder = new GlobalManagerBuilder(objectPostProcessor);

        for (FeatureConfigurerAdapter globalAdapter : configurers) {
            actionManagerBuilder.apply(globalAdapter);
        }

        return actionManagerBuilder.build();
    }
}
