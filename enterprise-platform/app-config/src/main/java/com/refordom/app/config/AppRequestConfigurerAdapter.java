package com.refordom.app.config;

import com.refordom.app.config.constant.AppConstant;
import com.refordom.app.config.core.AppAction;
import com.refordom.app.config.core.AppRequest;
import com.refordom.app.config.manager.AppManager;
import com.refordom.common.builder.ObjectPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用请求适配器，可可配置出各种业务接口，底层已经封装了所有处理逻辑，包括参数校验，业务处理，数据存储（包括事务），消息处理等
 *
 * @author pricess.wang
 * @date 2019/12/13 18:23
 */
public class AppRequestConfigurerAdapter implements AppRequestConfigurer<AppRequest> {

    private AppAction appAction;

    private ApplicationContext context;

    private AppManager appManager;

    // 事务管理器
    private PlatformTransactionManager transactionManager;

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

        appAction = new AppAction(objectPostProcessor, sharedObjects);

        appAction
                .paramsCheck();

        configure(appAction);

        return appAction;
    }

    private Map<Class<?>, Object> createSharedObjects() {
        Map<Class<?>, Object> sharedObjects = new HashMap<>();
        sharedObjects.put(ApplicationContext.class, context);
        sharedObjects.put(AppManager.class, appManager);
        sharedObjects.put(PlatformTransactionManager.class, transactionManager);
        return sharedObjects;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext context, AppManager appManager) {
        this.context = context;
        this.appManager = appManager;
        this.transactionManager = (PlatformTransactionManager) context.getBean(AppConstant.APP_TRANSACTION_MANAGER);
    }

    protected void configure(AppAction appAction) throws Exception {

    }

    @Override
    public void configure(AppRequest builder) throws Exception {

    }
}
