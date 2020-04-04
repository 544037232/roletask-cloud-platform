package com.omc.builder.api;

import com.omc.builder.ResultToken;

/**
 * 存储执行器，此接口标识将提供事务控制
 */
public interface StoreProvider {

    void provider(ResultToken token);
}
