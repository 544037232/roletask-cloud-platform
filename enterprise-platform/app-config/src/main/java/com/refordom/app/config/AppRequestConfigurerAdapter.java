package com.refordom.app.config;

import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.core.AppRequest;
import com.refordom.common.builder.ObjectPostProcessor;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public class AppRequestConfigurerAdapter implements AppConfigurer<AppRequest> {

    private AppAction appAction;

    private ObjectPostProcessor<Object> objectPostProcessor = new ObjectPostProcessor<Object>() {
        public <T> T postProcess(T object) {
            throw new IllegalStateException(
                    ObjectPostProcessor.class.getName()
                            + " is a required bean. Ensure you have used @EnableAppRequest and @Configuration");
        }
    };

    @Override
    public final void init(AppRequest builder) throws Exception {
        AppAction appAction = getAppAction();

        builder.addAppFilterChainBuilder(appAction);
    }

    private AppAction getAppAction() throws Exception {

        if (appAction != null) {
            return appAction;
        }

        appAction = new AppAction(objectPostProcessor);

        appAction
                .paramsCheck()
                .and()
                .auth();

        configure(appAction);

        return appAction;
    }

    protected void configure(AppAction appAction) throws Exception{

    }

    @Override
    public void configure(AppRequest builder) throws Exception {

    }
}
