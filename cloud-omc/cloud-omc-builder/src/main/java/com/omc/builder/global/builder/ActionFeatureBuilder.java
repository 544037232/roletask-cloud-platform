package com.omc.builder.global.builder;

import com.omc.builder.configurer.ConcurrentLockConfigurer;
import com.omc.builder.global.feature.FutureBuilder;
import com.omc.builder.global.feature.FutureManager;
import com.omc.object.AbstractConfiguredObjectBuilder;
import com.omc.object.ObjectBuilder;
import com.omc.object.ObjectPostProcessor;

/**
 * 请求插件构造器，用于构造不同场景下常用的插件，比如分布式锁，事务管理器等
 */
public class ActionFeatureBuilder extends AbstractConfiguredObjectBuilder<FutureManager, ActionFeatureBuilder>
        implements ObjectBuilder<FutureManager>, FutureBuilder<ActionFeatureBuilder> {


    private final String path;

    private boolean debugEnabled = false;

    public ActionFeatureBuilder(ObjectPostProcessor<Object> objectPostProcessor,String path) {
        super(objectPostProcessor);
        this.path = path;
    }

    @Override
    protected FutureManager performBuild() throws Exception {
        FutureManager futureManager = new FutureManager();
        futureManager.setPath(path);
        futureManager.setDebugEnabled(debugEnabled);
        return futureManager;
    }

    /**
     * 并发锁，可使用zookeeper，redis，ReentrantLock等
     */
    public ConcurrentLockConfigurer<ActionFeatureBuilder> concurrentLock() throws Exception {
        return getOrApply(new ConcurrentLockConfigurer<>());
    }

    public ActionFeatureBuilder debug(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
        return this;
    }
}
