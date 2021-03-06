package com.refordom.app.service.shelves.upper;

import com.pricess.omc.RequestConfigurerAdapter;
import com.pricess.omc.filter.ParamsCheckFilter;
import com.pricess.omc.serve.ServeAction;
import com.refordom.app.config.filter.AppDetailsFilter;
import com.refordom.app.model.AppDetailsManagerService;
import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.app.service.filter.AppDistroFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.annotation.Resource;

/**
 * 应用上架
 *
 * @author pricess.wang
 * @date 2019/12/31 17:24
 */
@Configuration
public class UpperShelfConfiguration extends RequestConfigurerAdapter {

    @Resource
    private AppDetailsManagerService appDetailsManagerService;

    @Resource
    private AppDistroManagerService appDistroManagerService;

    @Override
    protected void configure(ServeAction builder) throws Exception {
        builder.debug(true).requestMatcher().actionName("上架")
                .url(ActionConstant.UPPER_SHELF)
                .method(HttpMethod.GET)
                .and()
                .paramsCheck()
                .actionParamParser(new UpperShelfParamParser())
                .and()
                .addFilterAfter(new AppDetailsFilter(appDetailsManagerService), ParamsCheckFilter.class)
                .addFilterAfter(new AppDistroFilter(appDistroManagerService),AppDetailsFilter.class)
                .service()
                .addServiceProvider(new UpperShelfServiceProvider())
                .and()
                .store()
                .addStoreProvider(new UpperShelfStoreProvider(appDistroManagerService))
                .transaction()
                .jdbc();
    }


}
