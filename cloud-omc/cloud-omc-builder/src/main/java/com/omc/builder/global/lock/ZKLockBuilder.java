package com.omc.builder.global.lock;

import com.omc.builder.configurer.ConcurrentLockConfigurer;
import com.omc.builder.global.future.FutureBuilder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class ZKLockBuilder<B extends FutureBuilder<B>> implements LockBuilder {

    private ConcurrentLockConfigurer<B> concurrentLockConfigurer;

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

    private String connectionString;

    /**
     * 锁定key的前缀
     */
    private String lockPrefix;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    /**
     * 默认等待响应时间为0，立刻返回获取锁的状态
     */
    private int time = 0;

    public ZKLockBuilder(ConcurrentLockConfigurer<B> concurrentLockConfigurer) {
        this.concurrentLockConfigurer = concurrentLockConfigurer;
    }

    @Override
    public Lock builder() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, new ExponentialBackoffRetry(sleepTime, maxRetries));

        client.start();
        ZKLock zkLock = new ZKLock(client);
        zkLock.setPrefix(lockPrefix);
        zkLock.setTime(time);
        zkLock.setTimeUnit(timeUnit);
        return zkLock;
    }

    public ConcurrentLockConfigurer<B> and() {
        return concurrentLockConfigurer;
    }

    public ZKLockBuilder<B> connectionStrings(String connectionString) {
        this.connectionString = connectionString;
        return this;
    }

    public ZKLockBuilder<B> sleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public ZKLockBuilder<B> lockPrefix(String lockPrefix) {
        this.lockPrefix = lockPrefix;
        return this;
    }

    public ZKLockBuilder<B> maxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    public ZKLockBuilder<B> time(int time) {
        this.time = time;
        return this;
    }
}
