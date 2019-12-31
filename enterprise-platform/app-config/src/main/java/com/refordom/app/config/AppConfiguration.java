package com.refordom.app.config;

import com.refordom.app.config.manager.AppManager;
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
    public AppManager appManager(ApplicationContext context, ObjectPostProcessor<Object> objectPostProcessor) throws Exception {
        AppManagerBuilder appManagerBuilder = new AppManagerBuilder(objectPostProcessor, context);

        appManagerBuilder
                .appDetail().jdbc()
            .and()
                .distro().jdbc()
            .and()
                .running().jdbc()
            .and()
                .history().jdbc()
            .and()
                .installHistory().jdbc()
            .and()
                .madeDistro().jdbc();

        return appManagerBuilder.build();
    }

}
