package com.refordom.app.service.client;

import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.app.service.constant.ActionConstant;
import com.refordom.common.action.builder.ActionRequestConfigurerAdapter;
import com.refordom.common.action.builder.core.ServeAction;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 13:13
 */
@Configuration
public class EnvironmentConfiguration {

    @Configuration
    public static class CreateEnvironmentConfiguration extends ActionRequestConfigurerAdapter {

        @Resource
        private AppEnvironmentService appEnvironmentService;

        @Override
        protected void configure(ServeAction builder) throws Exception {
            builder.actionName("创建客户端应用环境")
                    .actionRequestMatcher(ActionConstant.ENVIRONMENT_CRUD)
                    .paramsCheck()
                    .actionParamParser(new EnvironmentParamParser())
                    .and()
                    .addFilter(new EnvironmentCreateFilter(), 2)
                    .addStoreProvider(new CreateEnvironmentStoreProvider(appEnvironmentService));
        }
    }

    @Configuration
    public static class EditEnvironmentConfiguration extends ActionRequestConfigurerAdapter {

        @Resource
        private AppEnvironmentService appEnvironmentService;

        @Override
        protected void configure(ServeAction builder) throws Exception {
            builder.actionName("修改客户端应用环境")
                    .actionRequestMatcher(ActionConstant.ENVIRONMENT_CRUD, HttpMethod.PUT)
                    .paramsCheck()
                    .actionParamParser(new EnvironmentParamParser.EnvironmentParamUpdateParser())
                    .and()
                    .addFilter(new EnvironmentUpdateFilter(), 2)
                    .addStoreProvider(new UpdateEnvironmentStoreProvider(appEnvironmentService));
        }
    }

    @Configuration
    public static class DeleteEnvironmentConfiguration extends ActionRequestConfigurerAdapter{
        @Resource
        private AppEnvironmentService appEnvironmentService;

        @Override
        protected void configure(ServeAction builder) throws Exception {
            builder.actionName("删除客户端应用环境")
                    .actionRequestMatcher(ActionConstant.ENVIRONMENT_CRUD, HttpMethod.DELETE)
                    .paramsCheck()
                    .actionParamParser(new EnvironmentParamParser.EnvironmentParamDeleteParser())
                    .and()
                    .addFilter(new EnvironmentDeleteFilter(appEnvironmentService), 2);
        }
    }
}
