package com.refordom.common.action.builder.lock;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.util.Assert;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/25 11:42
 */
public class DistributedLockFilter implements Filter {

    private final CuratorFramework client;

    private String lockPrefix;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 默认等待响应时间为0，立刻返回获取锁的状态
     */
    private int time;

    public DistributedLockFilter(CuratorFramework client) {
        this.client = client;
        this.lockPrefix = UUID.randomUUID().toString();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ActionDistributedLockHolder.getContext().setClient(new DistributedLockClient(client, lockPrefix, timeUnit, time));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void setLockPrefix(String lockPrefix) {
        Assert.notNull(lockPrefix, "this prefix can not be null");
        this.lockPrefix = lockPrefix;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
