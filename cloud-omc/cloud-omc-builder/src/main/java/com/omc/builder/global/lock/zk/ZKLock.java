package com.omc.builder.global.lock.zk;

import com.omc.builder.global.lock.Lock;
import org.apache.curator.framework.CuratorFramework;

import java.util.concurrent.TimeUnit;

public class ZKLock implements Lock {

    private final CuratorFramework curatorFramework;

    /**
     * 锁前缀
     */
    private String prefix;

    /**
     * 默认等待响应时间为0，立刻返回获取锁的状态
     */
    private int time = 0;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    public ZKLock(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

}
