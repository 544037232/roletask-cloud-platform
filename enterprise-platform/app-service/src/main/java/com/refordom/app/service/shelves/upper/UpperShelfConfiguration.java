package com.refordom.app.service.shelves.upper;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.filter.AppDetailsFilter;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.app.service.filter.AppDistroFilter;
import org.springframework.context.annotation.Configuration;

/**
 * 应用上架
 *
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class UpperShelfConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction
                .actionName("上架")
                .actionRequestMatcher(ActionConstant.UPPER_SHELF)
                .paramsCheck()
                .actionParamParser(new UpperShelfParamParser())
                .and()
                .addFilterAfter(new AppDistroFilter(appAction.getSharedObject(AppManager.class)), AppDetailsFilter.class)
                .addFilterAfter(new UpperShelfServiceFilter(), AppDistroFilter.class)
                .addStoreProvider(new UpperShelfStoreProvider(appAction.getSharedObject(AppManager.class)));
    }

}
