package com.refordom.app.service.download;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.filter.ParamsCheckFilter;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.service.constant.ActionConstant;
import org.springframework.context.annotation.Configuration;

/**
 * 应用下载
 *
 * @author pricess.wang
 * @date 2020/1/16 17:56
 */
@Configuration
public class DownloadConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction
                .actionName("下载")
                .actionRequestMatcher(ActionConstant.DOWNLOAD)
                .paramsCheck()
                .actionParamParser(new DownloadParamParser())
                .and()
                .addFilterBefore(new TokenParserFilter(), ParamsCheckFilter.class)
                .addFilterAfter(new DownloadRunningCheckFilter(appAction.getSharedObject(AppManager.class)), TokenParserFilter.class);
    }

}
