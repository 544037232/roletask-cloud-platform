package com.refordom.app.config;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/12 17:11
 */
public interface AppRequestMatcher {

    boolean matches(HttpServletRequest request);
}
