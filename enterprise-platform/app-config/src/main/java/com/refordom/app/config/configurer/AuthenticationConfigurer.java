package com.refordom.app.config.configurer;

import com.refordom.app.config.AppActionBuilder;
import com.refordom.app.config.filter.AuthenticationFilter;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:58
 */
public class AuthenticationConfigurer<H extends AppActionBuilder<H>>
        extends AbstractAppActionConfigurer<ActionParamsCheckConfigurer<H>, H> {

    @Override
    public void init(H builder) throws Exception {
        builder.addFilter(new AuthenticationFilter());
    }
}
