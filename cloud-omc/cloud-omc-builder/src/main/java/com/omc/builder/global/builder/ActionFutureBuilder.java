package com.omc.builder.global.builder;

import com.omc.builder.api.TransactionManager;
import com.omc.builder.configurer.ConcurrentLockConfigurer;
import com.omc.builder.configurer.TransactionManagerConfigurer;
import com.omc.builder.global.future.FutureBuilder;
import com.omc.builder.global.future.FutureManager;
import com.omc.builder.global.transaction.DefaultNoneTransactionManager;
import com.omc.object.AbstractConfiguredObjectBuilder;
import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectPostProcessor;
import org.springframework.context.ApplicationContext;

/**
 * 请求插件构造器，用于构造不同场景下常用的插件，比如分布式锁，事务管理器等
 */
public class ActionFutureBuilder extends AbstractConfiguredObjectBuilder<FutureManager, ActionFutureBuilder>
        implements ObjectBuilder<FutureManager>, FutureBuilder<ActionFutureBuilder> {
    /**
     * 事务管理器
     */
    private TransactionManager transactionManager = new DefaultNoneTransactionManager();

    private ApplicationContext context;

    private final String path;

    private boolean debugEnabled = false;

    public ActionFutureBuilder(ObjectPostProcessor<Object> objectPostProcessor, ApplicationContext context, String path) {
        super(objectPostProcessor);
        this.context = context;
        this.path = path;
    }

    @Override
    protected FutureManager performBuild() throws Exception {
        FutureManager futureManager = new FutureManager();
        futureManager.setPath(path);
        futureManager.setDebugEnabled(debugEnabled);
        futureManager.setTransactionManager(transactionManager);
        return futureManager;
    }

    @Override
    public ActionFutureBuilder transactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        return this;
    }

    /**
     * 事务管理器
     */
    public TransactionManagerConfigurer<ActionFutureBuilder> transaction() throws Exception {
        return getOrApply(new TransactionManagerConfigurer<>(context));
    }

    /**
     * 并发锁，可使用zookeeper，redis，ReentrantLock等
     */
    public ConcurrentLockConfigurer<ActionFutureBuilder> concurrentLock() throws Exception {
        return getOrApply(new ConcurrentLockConfigurer<>());
    }

    public ActionFutureBuilder debug(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        return this;
    }
}
