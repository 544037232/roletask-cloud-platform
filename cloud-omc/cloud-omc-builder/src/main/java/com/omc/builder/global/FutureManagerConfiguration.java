package com.omc.builder.global;

import com.omc.builder.global.builder.GlobalManagerBuilder;
import com.omc.object.ObjectPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;

public class FutureManagerConfiguration {

    @Bean
    public GlobalManager actionManagerBuilder(ObjectPostProcessor<Object> objectPostProcessor,
                                              List<FutureConfigurerAdapter> configurers) throws Exception {
        configurers.sort(AnnotationAwareOrderComparator.INSTANCE);

        GlobalManagerBuilder actionManagerBuilder = new GlobalManagerBuilder(objectPostProcessor);

        for (FutureConfigurerAdapter globalAdapter : configurers) {
            actionManagerBuilder.apply(globalAdapter);
        }

        return actionManagerBuilder.build();
    }
}
