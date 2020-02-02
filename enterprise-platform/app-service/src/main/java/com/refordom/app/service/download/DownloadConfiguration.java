package com.refordom.app.service.download;

import com.refordom.app.model.AppDistroManagerService;
import com.refordom.app.model.AppRunningManagerService;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.app.service.filter.AppDistroFilter;
import com.refordom.common.action.builder.ActionRequestConfigurerAdapter;
import com.refordom.common.action.builder.core.ParamsCheckFilter;
import com.refordom.common.action.builder.core.ServeAction;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 应用下载
 *
 * @author pricess.wang
 * @date 2020/1/16 17:56
 */
@Configuration
public class DownloadConfiguration extends ActionRequestConfigurerAdapter {

    @Resource
    private AppDistroManagerService appDistroManagerService;

    @Resource
    private AppRunningManagerService appRunningManagerService;

    @Override
    protected void configure(ServeAction builder) throws Exception {

        builder.resultToken(DownloadToken.class)
                .addServiceProvider(new RunningServiceProvider())
                .actionName("下载")
                .actionRequestMatcher(ActionConstant.DOWNLOAD)
                .paramsCheck()
                .actionParamParser(new DownloadParamParser())
                .and()
                .addFilter(new TokenParserFilter(), 1)
                .addFilter(new AppDistroFilter(appDistroManagerService), 3)
                .addFilter(new DownloadRunningCheckFilter(appRunningManagerService), 4)
                .sortReset(ParamsCheckFilter.class, 2);

    }

}
