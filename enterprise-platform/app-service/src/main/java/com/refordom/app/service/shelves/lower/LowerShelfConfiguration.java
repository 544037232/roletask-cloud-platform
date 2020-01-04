package com.refordom.app.service.shelves.lower;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.service.constant.ActionConstant;
import org.springframework.context.annotation.Configuration;

/**
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class LowerShelfConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction
                .actionRequestMatcher(ActionConstant.LOWER_SHELF)
                .paramsCheck();
    }

}
