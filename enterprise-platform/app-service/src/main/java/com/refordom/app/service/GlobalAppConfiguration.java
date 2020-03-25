package com.refordom.app.service;

import com.omc.builder.global.FutureConfigurerAdapter;
import com.omc.builder.global.builder.ActionFutureBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalAppConfiguration extends FutureConfigurerAdapter {

    @Override
    protected void configure(ActionFutureBuilder futureBuilder) throws Exception {
        futureBuilder.transaction()
                .jdbc()
                .and()
                .and()
                .concurrentLock()
                .zk()
                .connectionStrings("192.168.3.72:2181,192.168.3.72:2182,192.168.3.72:2183");
    }
}
