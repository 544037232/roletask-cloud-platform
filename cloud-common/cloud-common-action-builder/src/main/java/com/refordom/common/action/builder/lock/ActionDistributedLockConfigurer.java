package com.refordom.common.action.builder.lock;

import com.refordom.common.action.builder.ActionBuilder;
import com.refordom.common.action.builder.configurer.AbstractActionConfigurer;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁配置
 *
 * @author pricess.wang
 * @date 2019/12/13 18:50
 */
public final class ActionDistributedLockConfigurer<B extends ActionBuilder<B>>
        extends AbstractActionConfigurer<ActionDistributedLockConfigurer<B>, B> {

    /**
     * 心跳检测,ms
     */
    private final int DEFAULT_SLEEP_TIME_MS = 1000;

    /**
     * 失败重连次数
     */
    private final int DEFAULT_MAX_RETRIES = 3;

    private int sleepTime = DEFAULT_SLEEP_TIME_MS;

    private int maxRetries = DEFAULT_MAX_RETRIES;

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(sleepTime, maxRetries);

    private String connectionString;

    /**
     * 锁定key的前缀
     */
    private String lockPrefix;

    private CuratorFramework client;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    /**
     * 默认等待响应时间为0，立刻返回获取锁的状态
     */
    private int time = 0;

    @Override
    public final void init(B builder) throws Exception {
        client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
        client.start();
    }

    @Override
    public void configure(B builder) throws Exception {
        DistributedLockFilter distributedLockFilter = new DistributedLockFilter(client);

        if (lockPrefix != null) {
            distributedLockFilter.setLockPrefix(lockPrefix);
        }

        distributedLockFilter.setTime(time);
        distributedLockFilter.setTimeUnit(timeUnit);
        builder.addFilter(distributedLockFilter, -100000);

    }

    public ActionDistributedLockConfigurer<B> sleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public ActionDistributedLockConfigurer<B> lockPrefix(String lockPrefix) {
        this.lockPrefix = lockPrefix;
        return this;
    }

    public ActionDistributedLockConfigurer<B> maxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    public ActionDistributedLockConfigurer<B> addConnections(String connectionString) {
        this.connectionString = connectionString;
        return this;
    }

    public ActionDistributedLockConfigurer<B> lockWaitTime(int time) {
        this.time = time;
        return this;
    }

    public ActionDistributedLockConfigurer<B> lockTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }
}
