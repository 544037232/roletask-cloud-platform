package com.refordom.app.config.provisioning.configurer;

import com.refordom.app.config.manager.AppProviderManagerBuilder;
import com.refordom.app.config.provisioning.InMemoryAppDetailsManager;

/**
 * @author pricess.wang
 * @date 2019/12/26 16:10
 */
public class AppInMemoryConfigurer<B extends AppProviderManagerBuilder<B>> extends AppDetailsManagerConfigurer<B, AppInMemoryConfigurer<B>> {

    public AppInMemoryConfigurer() {
        super(new InMemoryAppDetailsManager());
    }
}
