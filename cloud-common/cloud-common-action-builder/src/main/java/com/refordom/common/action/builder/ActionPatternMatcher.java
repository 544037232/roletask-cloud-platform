package com.refordom.common.action.builder;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求校验器
 *
 * @author pricess.wang
 * @date 2019/12/13 18:37
 */
public final class ActionPatternMatcher implements ActionRequestMatcher {

    private final String action;

    private final HttpMethod method;

    public ActionPatternMatcher(String action, HttpMethod method) {
        this.action = action;
        this.method = method;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return request.getRequestURI()
                .equals(action) && method.matches(request.getMethod());
    }
}
