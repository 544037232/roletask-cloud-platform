package com.refordom.app.model.exception;

/**
 * @author pricess.wang
 * @date 2020/1/16 16:39
 */
@Deprecated
public class ConcurrentException extends RuntimeException{

    public ConcurrentException(String msg) {
        super(msg);
    }
}
