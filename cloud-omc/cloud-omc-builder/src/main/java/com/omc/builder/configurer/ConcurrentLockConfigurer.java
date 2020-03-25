package com.omc.builder.configurer;

import com.omc.builder.global.future.FutureBuilder;
import com.omc.builder.global.lock.LockBuilder;
import com.omc.builder.global.lock.segment.SegmentLockBuilder;
import com.omc.builder.global.lock.zk.ZKLockBuilder;
import com.omc.builder.util.LockUtils;

public class ConcurrentLockConfigurer<B extends FutureBuilder<B>>
        extends AbstractManagerConfigurer<TransactionManagerConfigurer<B>, B> {

    private LockBuilder lockBuilder;

    @Override
    public void init(B builder) throws Exception {
        if (lockBuilder == null) {
            this.lockBuilder = new SegmentLockBuilder();
        }
    }

    @Override
    public void configure(B builder) throws Exception {
        LockUtils.setLock(lockBuilder.builder());
    }

    public ZKLockBuilder<B> zk() {
        ZKLockBuilder<B> zkLockBuilder = new ZKLockBuilder<>(this);
        this.lockBuilder = zkLockBuilder;
        return zkLockBuilder;
    }
}
