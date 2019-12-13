package com.refordom.app.config.config;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.business.lowershelf.AppStateUpperCheckFilter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.core.AppRequest;
import org.springframework.context.annotation.Configuration;

/**
 * @author pricess.wang
 * @date 2019/12/13 19:02
 */
@Configuration
public class LowerShelfConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction.actionRequestMatcher("lowerShelf")
                .addFilter(new AppStateUpperCheckFilter());
    }

}
