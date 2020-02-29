package com.refordom.common.action.builder.lock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/25 17:04
 */
public interface DistributedLockContext {

    void setClient(DistributedLockClient client);

    DistributedLockClient getClient();

    void setLockInterProcessMutex(InterProcessMutex interProcessMutex);

    InterProcessMutex getInterProcessMutex();
}
