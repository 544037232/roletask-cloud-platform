package com.refordom.app.config.config;

import com.refordom.app.config.AppRequestConfigurerAdapter;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.core.AppRequest;
import org.springframework.context.annotation.Configuration;

/**
 * @author pricess.wang
 * @date 2019/12/13 19:02
 */
@Configuration
public class UpperShelfConfiguration extends AppRequestConfigurerAdapter {

    @Override
    protected void configure(AppAction appAction) throws Exception {
        appAction.paramsCheck().paramsEmptyCheck("app_id","应用ID不能为空");
    }

    @Override
    public void configure(AppRequest builder) throws Exception {
        super.configure(builder);
    }
}
