package com.refordom.common.action.builder.lock;

import com.refordom.common.action.builder.context.ActionContextHolderStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/26 9:25
 */
@Slf4j
public class ThreadLocalActionDistributedLockHolderStrategy implements ActionContextHolderStrategy<DistributedLockContext> {

    private static final ThreadLocal<DistributedLockContext> contextHolder = new ThreadLocal<>();

    public DistributedLockContext createEmptyContext() {
        return new DistributedLockContextImpl();
    }

    @Override
    public void clearContext() {
        InterProcessMutex interProcessMutex = contextHolder.get().getInterProcessMutex();

        if (interProcessMutex != null) {
            try {
                interProcessMutex.release();
            } catch (Exception e) {
                log.error("unLock error:" + e.getMessage());
            }
        }
        contextHolder.remove();
    }

    public DistributedLockContext getContext() {
        DistributedLockContext ctx = contextHolder.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            setContext(ctx);
        }
        return ctx;
    }

    public void setContext(DistributedLockContext context) {
        contextHolder.set(context);
    }

}
