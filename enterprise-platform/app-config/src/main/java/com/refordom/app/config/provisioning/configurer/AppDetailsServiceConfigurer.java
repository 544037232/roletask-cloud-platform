package com.refordom.app.config.provisioning.configurer;

import com.refordom.app.config.manager.AppProviderManagerBuilder;
import com.refordom.app.model.AppDetailsManager;

/**
 * @author pricess.wang
 * @date 2019/12/26 16:11
 */
public class AppDetailsServiceConfigurer<B extends AppProviderManagerBuilder<B>, C extends AppDetailsServiceConfigurer<B, C, A>, A extends AppDetailsManager>
        extends AbstractDaoAppConfigurer<B, C, A> {

    protected AppDetailsServiceConfigurer(A appDetailsManager) {
        super(appDetailsManager);
    }

    @Override
    public void configure(B builder) throws Exception {
        initAppDetailsService();
    }

    protected void initAppDetailsService() throws Exception {
    }
}
