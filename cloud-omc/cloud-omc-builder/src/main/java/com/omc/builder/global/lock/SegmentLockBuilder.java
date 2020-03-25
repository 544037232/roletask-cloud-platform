package com.omc.builder.global.lock;

public class SegmentLockBuilder implements LockBuilder {

    @Override
    public Lock builder() {
        return new SegmentLock();
    }
}
