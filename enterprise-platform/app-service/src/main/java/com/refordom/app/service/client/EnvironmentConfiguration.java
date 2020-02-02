package com.refordom.app.service.client;

import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.common.action.builder.ActionRequestConfigurerAdapter;
import com.refordom.common.action.builder.core.ServeAction;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 13:13
 */
@Configuration
public class EnvironmentConfiguration extends ActionRequestConfigurerAdapter {

    @Resource
    private AppEnvironmentService appEnvironmentService;

    @Override
    protected void configure(ServeAction builder) throws Exception {
        builder.actionName("创建客户端应用环境")
                .actionRequestMatcher(ActionConstant.ENVIRONMENT_CRUD)
                .paramsCheck()
                .actionParamParser(new EnvironmentParamParser())
                .and()
                .addFilter(new EnvironmentCheckExistFilter(appEnvironmentService),2)
        ;
    }

}
