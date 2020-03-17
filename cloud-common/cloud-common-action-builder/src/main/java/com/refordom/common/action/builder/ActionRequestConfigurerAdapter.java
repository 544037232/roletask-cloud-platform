package com.refordom.common.action.builder;

import com.refordom.common.action.builder.constant.ActionConstant;
import com.refordom.common.action.builder.core.ServeAction;
import com.refordom.common.action.builder.core.ServeRequest;
import com.refordom.common.action.builder.global.ActionGlobalConfiguration;
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
public class ActionRequestConfigurerAdapter implements RequestConfigurer<ServeRequest> {

    private ServeAction serveAction;

    private ApplicationContext context;

    // 事务管理器
    private PlatformTransactionManager transactionManager;

    private ActionGlobalConfiguration actionGlobalConfiguration;

    private ObjectPostProcessor<Object> objectPostProcessor = new ObjectPostProcessor<Object>() {
        public <T> T postProcess(T object) {
            throw new IllegalStateException(
                    ObjectPostProcessor.class.getName()
                            + " is a required bean. Ensure you have used @EnableAppRequest and @Configuration");
        }
    };

    @Override
    public final void init(ServeRequest builder) throws Exception {
        ServeAction serveAction = getServeAction();

        builder.addFilterChainBuilder(serveAction);

        builder.setActionManager(actionGlobalConfiguration.getActionManager());
    }

    private ServeAction getServeAction() throws Exception {

        if (serveAction != null) {
            return serveAction;
        }
        Map<Class<?>, Object> sharedObjects = createSharedObjects();

        serveAction = new ServeAction(objectPostProcessor, sharedObjects);

        serveAction
                .paramsCheck();

        configure(serveAction);

        return serveAction;
    }

    private Map<Class<?>, Object> createSharedObjects() {
        Map<Class<?>, Object> sharedObjects = new HashMap<>();
        sharedObjects.put(ApplicationContext.class, context);
        sharedObjects.put(PlatformTransactionManager.class, transactionManager);
        return sharedObjects;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
        this.transactionManager = (PlatformTransactionManager) context.getBean(ActionConstant.ACTION_TRANSACTION_MANAGER);
    }

    @Autowired
    public void setAuthenticationConfiguration(
            ActionGlobalConfiguration actionGlobalConfiguration) {
        this.actionGlobalConfiguration = actionGlobalConfiguration;
    }

    protected void configure(ServeAction builder) throws Exception {

    }

    @Override
    public void configure(ServeRequest builder) throws Exception {

    }
}
