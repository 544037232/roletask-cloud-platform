package com.refordom.app.config;

import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.core.AppRequest;
import com.refordom.app.config.provisioning.AppModelManagerService;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public class AppRequestConfigurerAdapter implements AppConfigurer<AppRequest> {

    private AppAction appAction;

    private ApplicationContext context;

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
        Map<Class<?>, Object> sharedObjects = createSharedObjects();

        appAction = new AppAction(objectPostProcessor,sharedObjects);

        appAction
                .paramsCheck();

        configure(appAction);

        return appAction;
    }

    private Map<Class<?>, Object> createSharedObjects() {
        Map<Class<?>, Object> sharedObjects = new HashMap<>();
        sharedObjects.put(ApplicationContext.class, context);
        sharedObjects.put(AppModelManagerService.class, context.getBean(AppModelManagerService.class));
//        sharedObjects.put(AppReleaseManager.class, context.getBean(AppReleaseManager.class));
//        sharedObjects.put(AppRunningManager.class, context.getBean(AppRunningManager.class));
//        sharedObjects.put(AppHistoryManager.class, context.getBean(AppHistoryManager.class));
        return sharedObjects;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    protected void configure(AppAction appAction) throws Exception {

    }

    @Override
    public void configure(AppRequest builder) throws Exception {

    }
}
