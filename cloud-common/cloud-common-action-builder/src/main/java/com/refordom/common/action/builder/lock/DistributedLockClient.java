package com.refordom.common.action.builder.lock;

import org.apache.curator.framework.CuratorFramework;

import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/26 9:30
 */
public class DistributedLockClient {

    /**
     * zookeeper登陆客户端
     */
    private CuratorFramework connection;

    /**
     * 锁定的前缀
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

    public DistributedLockClient(CuratorFramework connection, String lockPrefix) {
        this.connection = connection;
        this.lockPrefix = lockPrefix;
    }

    public DistributedLockClient(CuratorFramework connection, String lockPrefix, TimeUnit timeUnit, int time) {
        this(connection,lockPrefix);
        this.timeUnit = timeUnit;
        this.time = time;
    }

    public CuratorFramework getConnection() {
        return connection;
    }

    public void setConnection(CuratorFramework connection) {
        this.connection = connection;
    }

    public String getLockPrefix() {
        return lockPrefix;
    }

    public void setLockPrefix(String lockPrefix) {
        this.lockPrefix = lockPrefix;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "DistributedLockClient{" +
                "connection=" + connection +
                ", lockPrefix='" + lockPrefix + '\'' +
                '}';
    }
}
