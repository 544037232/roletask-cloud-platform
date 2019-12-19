package com.refordom.app.config.core;

import org.springframework.util.Assert;

/**
 * @author pricess.wang
 * @date 2019/12/19 15:28
 */
public class ThreadLocalAppContextHolderStrategy implements AppContextHolderStrategy {

    private static final ThreadLocal<AppContext> contextHolder = new ThreadLocal<>();

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public AppContext getContext() {
        AppContext ctx = contextHolder.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }
        return ctx;
    }

    @Override
    public void setContext(AppContext context) {
        Assert.notNull(context, "Only non-null AppContext instances are permitted");
        contextHolder.set(context);
    }

    @Override
    public AppContext createEmptyContext() {
        return new AppContextImpl();
    }
}
