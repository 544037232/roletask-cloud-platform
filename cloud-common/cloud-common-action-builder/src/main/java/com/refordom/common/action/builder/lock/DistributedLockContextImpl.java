package com.refordom.common.action.builder.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/26 9:17
 */
public class DistributedLockContextImpl implements DistributedLockContext {

    private DistributedLockClient client;

    private InterProcessMutex interProcessMutex;

    @Override
    public void setClient(DistributedLockClient client) {
        this.client = client;
    }

    @Override
    public DistributedLockClient getClient() {
        return client;
    }

    @Override
    public void setLockInterProcessMutex(InterProcessMutex interProcessMutex) {
        this.interProcessMutex = interProcessMutex;
    }

    @Override
    public InterProcessMutex getInterProcessMutex() {
        return interProcessMutex;
    }
}
