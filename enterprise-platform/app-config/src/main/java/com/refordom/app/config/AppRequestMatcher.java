package com.refordom.app.config;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求校验器，每个接口对应一个请求校验器，通过请求校验器路由适配到对应的应用请求过滤器链
 *
 * @author pricess.wang
 * @date 2019/12/12 17:11
 */
public interface AppRequestMatcher {

    boolean matches(HttpServletRequest request);
}
