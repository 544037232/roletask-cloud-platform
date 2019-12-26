package com.refordom.app.config;

import com.refordom.app.config.manager.AppManagerBuilder;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author pricess.wang
 * @date 2019/12/26 15:23
 */
public class AppConfiguration {

    @Bean
    public AppManagerBuilder appManagerBuilder(ApplicationContext context, ObjectPostProcessor<Object> objectPostProcessor) {
        return new AppManagerBuilder(objectPostProcessor, context);
    }

}
