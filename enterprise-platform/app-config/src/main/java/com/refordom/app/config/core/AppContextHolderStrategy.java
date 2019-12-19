package com.refordom.app.config.core;

/**
 * @author pricess.wang
 * @date 2019/12/19 15:25
 */
public interface AppContextHolderStrategy {

    void clearContext();

    AppContext getContext();

    void setContext(AppContext context);

    AppContext createEmptyContext();
}
