package com.refordom.app.config.provisioning.configurer;

import com.refordom.app.config.manager.AppManager;
import com.refordom.app.config.manager.AppProviderManagerBuilder;
import com.refordom.app.model.AppDetailsManager;
import com.refordom.common.builder.ObjectConfigurerAdapter;

/**
 * @author pricess.wang
 * @date 2019/12/26 16:27
 */
public abstract class AppDetailsAwareConfigurer<B extends AppProviderManagerBuilder<B>,A extends AppDetailsManager>
        extends ObjectConfigurerAdapter<AppManager,B> {

    public abstract A getAppDetailManager();
}
