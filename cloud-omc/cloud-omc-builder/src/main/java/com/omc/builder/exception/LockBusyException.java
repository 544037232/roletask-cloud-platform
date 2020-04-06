package com.omc.builder.exception;

/**
 * 锁繁忙异常
 */
public class LockBusyException extends RuntimeException  {

    public LockBusyException(String msg) {
        super(msg);
    }
}
