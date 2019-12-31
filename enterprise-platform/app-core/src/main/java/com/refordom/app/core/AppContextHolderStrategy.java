package com.refordom.app.core;

/**
 * 应用上下文执行器策略，当前只实现了ThreadLocal
 *
 * @author pricess.wang
 * @date 2019/12/19 15:25
 */
public interface AppContextHolderStrategy {

    void clearContext();

    AppContext getContext();

    void setContext(AppContext context);

    AppContext createEmptyContext();
}
