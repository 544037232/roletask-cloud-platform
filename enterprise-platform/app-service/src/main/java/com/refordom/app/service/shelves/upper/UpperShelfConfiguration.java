package com.refordom.app.service.shelves.upper;

import com.refordom.app.config.filter.AppDetailsFilter;
import com.refordom.app.model.AppDetailsManagerService;
import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.app.service.filter.AppDistroFilter;
import com.refordom.common.action.builder.ActionRequestConfigurerAdapter;
import com.refordom.common.action.builder.core.ServeAction;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 应用上架
 *
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class UpperShelfConfiguration extends ActionRequestConfigurerAdapter {

    @Resource
    private AppDetailsManagerService appDetailsManagerService;

    @Resource
    private AppDistroManagerService appDistroManagerService;

    @Override
    protected void configure(ServeAction builder) throws Exception {
        builder.actionName("上架")
                .actionRequestMatcher(ActionConstant.UPPER_SHELF)
                .paramsCheck()
                .actionParamParser(new UpperShelfParamParser())
                .and()
                .addFilter(new AppDetailsFilter(appDetailsManagerService),2)
                .addFilter(new AppDistroFilter(appDistroManagerService),3)
                .addFilter(new UpperShelfServiceFilter(),4)
                .addStoreProvider(new UpperShelfStoreProvider(appDistroManagerService));
    }


}
