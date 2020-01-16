package com.refordom.app.service.shelves.lower;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.core.AbstractAppPrimaryFilter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.service.constant.ActionConstant;
import org.springframework.context.annotation.Configuration;

/**
 * 应用下架
 *
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class LowerShelfConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction
                .actionName("下架")
                .actionRequestMatcher(ActionConstant.LOWER_SHELF)
                .addFilterBefore(new LowerShelfServiceFilter(appAction.getSharedObject(AppManager.class)), AbstractAppPrimaryFilter.class)
                .addStoreProvider(new LowerShelfStoreProvider(appAction.getSharedObject(AppManager.class)));
    }

}
