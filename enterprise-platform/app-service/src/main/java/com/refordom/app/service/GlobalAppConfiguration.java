package com.refordom.app.service;

import com.omc.builder.global.FeatureConfigurerAdapter;
import com.omc.builder.global.builder.ActionFeatureBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalAppConfiguration extends FeatureConfigurerAdapter {

    @Override
    protected void configure(ActionFeatureBuilder futureBuilder) throws Exception {
        futureBuilder
                .debug(true);
    }
}
