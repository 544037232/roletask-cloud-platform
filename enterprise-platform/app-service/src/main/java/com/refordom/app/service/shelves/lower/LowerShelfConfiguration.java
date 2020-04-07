package com.refordom.app.service.shelves.lower;

import com.pricess.omc.RequestConfigurerAdapter;
import com.pricess.omc.filter.ParamsCheckFilter;
import com.pricess.omc.serve.ServeAction;
import com.refordom.app.config.DefaultParamParser;
import com.refordom.app.config.filter.AppDetailsFilter;
import com.refordom.app.model.AppDetailsManagerService;
import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.app.service.filter.AppDistroFilter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 应用下架
 *
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class LowerShelfConfiguration extends RequestConfigurerAdapter {

    @Resource
    private AppDistroManagerService appDistroManagerService;

    @Resource
    private AppDetailsManagerService appDetailsManagerService;

    @Override
    protected void configure(ServeAction builder) throws Exception {
        builder.debug(true).requestMatcher()
                .actionName("下架")
                .url(ActionConstant.LOWER_SHELF)
                .and()
                .paramsCheck()
                .actionParamParser(new DefaultParamParser())
                .and()
                .addFilterAfter(new AppDetailsFilter(appDetailsManagerService), ParamsCheckFilter.class)
                .addFilterAfter(new AppDistroFilter(appDistroManagerService), AppDetailsFilter.class)
                .addFilterAfter(new LowerShelfServiceFilter(), AppDistroFilter.class);
    }
}
