package com.omc.builder.api;

import com.omc.builder.ResultToken;

/**
 * 服务执行器
 */
public interface ServiceProvider {

    ResultToken provider(ResultToken token);

    boolean supports(Class<?> context);
}
