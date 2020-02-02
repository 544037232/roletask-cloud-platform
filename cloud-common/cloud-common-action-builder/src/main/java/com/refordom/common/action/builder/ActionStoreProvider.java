package com.refordom.common.action.builder;

/**
 * 存储处理器，此处理器包含事务处理
 *
 * @author pricess.wang
 * @date 2019/12/17 16:33
 */
public interface ActionStoreProvider {

    <T> void provider(T result);

    boolean supports(Class<?> rst);
}
