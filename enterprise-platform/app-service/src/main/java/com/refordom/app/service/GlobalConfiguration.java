package com.refordom.app.service;

import com.refordom.app.config.constant.AppConstant;
import com.refordom.common.action.builder.global.ActionManagerBuilder;
import com.refordom.common.action.builder.global.GlobalActionConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfiguration extends GlobalActionConfigurerAdapter {

    @Override
    public void configure(ActionManagerBuilder builder) throws Exception {
        builder.debug(true)
                .addUrlPattern(AppConstant.APP_URL_PATTERNS);
    }
}
