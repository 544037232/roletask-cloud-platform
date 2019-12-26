package com.refordom.app.config.provisioning.configurer;

import com.refordom.app.config.manager.AppProviderManagerBuilder;
import com.refordom.app.model.AppDetailsManager;

/**
 * @author pricess.wang
 * @date 2019/12/26 16:11
 */
public class AppDetailsManagerConfigurer<B extends AppProviderManagerBuilder<B>, C extends AppDetailsManagerConfigurer<B, C>>
        extends AppDetailsServiceConfigurer<B, C, AppDetailsManager> {

    protected AppDetailsManagerConfigurer(AppDetailsManager appDetailsManager) {
        super(appDetailsManager);
    }

}
