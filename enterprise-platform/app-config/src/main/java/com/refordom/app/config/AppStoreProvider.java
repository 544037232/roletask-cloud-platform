package com.refordom.app.config;

/**
 * 应用存储处理器，此处理器包含事务处理
 *
 * @author pricess.wang
 * @date 2019/12/17 16:33
 */
public interface AppStoreProvider {

    <T> void provider(T result);

    boolean supports(Class<?> rst);
}
