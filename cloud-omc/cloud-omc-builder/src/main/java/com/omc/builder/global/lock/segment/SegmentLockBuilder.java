package com.omc.builder.global.lock.segment;

import com.omc.builder.global.lock.Lock;
import com.omc.builder.global.lock.LockBuilder;

public class SegmentLockBuilder implements LockBuilder {

    @Override
    public Lock builder() {
        return new SegmentLock();
    }
}
