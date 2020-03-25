package com.omc.builder.util;

import com.omc.builder.global.lock.Lock;

public class LockUtils {

    private static Lock lock;

    public static void setLock(Lock lock) {
        LockUtils.lock = lock;
    }

    public static boolean tryLock(String key) {
        return true;
    }

    public static boolean isLocked(String key) {
        return true;
    }

    public static void lock(String key) {

    }

    public static void unLock(String key) {
    }

}
