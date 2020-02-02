package com.refordom.app.service.shelves.lower;

import com.refordom.app.config.DefaultParamParser;
import com.refordom.app.config.constant.AppConstant;
import com.refordom.app.config.filter.AppDetailsFilter;
import com.refordom.app.model.AppDetailsManagerService;
import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.app.service.filter.AppDistroFilter;
import com.refordom.common.action.builder.ActionRequestConfigurerAdapter;
import com.refordom.common.action.builder.core.ServeAction;
import com.refordom.common.action.builder.core.ServeRequest;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 应用下架
 *
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class LowerShelfConfiguration extends ActionRequestConfigurerAdapter {

    @Resource
    private AppDistroManagerService appDistroManagerService;

    @Resource
    private AppDetailsManagerService appDetailsManagerService;

    @Override
    protected void configure(ServeAction builder) throws Exception {
        builder.actionName("下架")
                .paramsCheck()
                .actionParamParser(new DefaultParamParser())
                .and()
                .actionRequestMatcher(ActionConstant.LOWER_SHELF)
                .addFilter(new AppDetailsFilter(appDetailsManagerService),2)
                .addFilter(new AppDistroFilter(appDistroManagerService),3)
                .addFilter(new LowerShelfServiceFilter(),4)
                .addStoreProvider(new LowerShelfStoreProvider(appDistroManagerService));
    }

    @Override
    public void configure(ServeRequest builder) throws Exception {
        builder
                .debug(true)
                .addUrlPattern(AppConstant.APP_URL_PATTERNS);
    }
}
