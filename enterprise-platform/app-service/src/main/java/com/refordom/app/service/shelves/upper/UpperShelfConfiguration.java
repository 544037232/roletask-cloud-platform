package com.refordom.app.service.shelves.upper;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.core.AbstractAppPrimaryFilter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.service.constant.ActionConstant;
import org.springframework.context.annotation.Configuration;

/**
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class UpperShelfConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction
                .actionRequestMatcher(ActionConstant.UPPER_SHELF)
                .paramsCheck()
                .actionParamParser(new UpperShelfActionParamParser())
                .and()
                .addFilterAfter(new UpperShelfServiceFilter(appAction.getSharedObject(AppManager.class)), AbstractAppPrimaryFilter.class);
    }

}
