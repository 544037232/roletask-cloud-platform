package com.refordom.common.action.builder.context;

/**
 * 应用上下文执行器策略，当前只实现了ThreadLocal
 *
 * @author pricess.wang
 * @date 2019/12/19 15:25
 */
public interface ActionContextHolderStrategy {

    void clearContext();

    ActionContext getContext();

    void setContext(ActionContext context);

    ActionContext createEmptyContext();
}
