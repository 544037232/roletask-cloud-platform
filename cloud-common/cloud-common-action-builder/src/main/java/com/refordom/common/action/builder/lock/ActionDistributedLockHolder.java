package com.refordom.common.action.builder.lock;

import com.refordom.common.action.builder.context.ActionContextHolderStrategy;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * 分布式锁操作上下文
 *
 * @author pricess.wang
 * @date 2019/12/31
 */
public class ActionDistributedLockHolder {

    private static ActionContextHolderStrategy<DistributedLockContext> strategy = new ThreadLocalActionDistributedLockHolderStrategy();

    public static boolean lock(String key) {
        DistributedLockClient curatorFramework = strategy.getContext().getClient();

        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework.getConnection(), "/dis-lock-" + curatorFramework.getLockPrefix() + key);

        try {
            boolean locked = interProcessMutex.acquire(curatorFramework.getTime(), curatorFramework.getTimeUnit());

            if (locked){
                strategy.getContext().setLockInterProcessMutex(interProcessMutex);
            }
            return locked;
        } catch (Exception e) {
            return false;
        }
    }

    public static DistributedLockContext getContext() {
        return strategy.getContext();
    }

    public static void clearContext() {
        strategy.clearContext();
    }

    public static void setContext(DistributedLockContext context) {
        strategy.setContext(context);
    }

    public static DistributedLockContext createEmptyContext() {
        return strategy.createEmptyContext();
    }

}
