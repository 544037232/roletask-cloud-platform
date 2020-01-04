package com.refordom.app.config;

/**
 * 应用业务处理器，主要处理业务的校验和数据的处理，没有包含事务处理
 *
 * @author pricess.wang
 * @date 2019/12/17 16:33
 */
public interface AppServiceProvider {

    AppToken provider(AppToken appToken);

    boolean supports(Class<?> context);
}
