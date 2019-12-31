package com.refordom.app.config;

import com.refordom.app.config.constant.AppConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/13 18:37
 */
public final class AppActionMatcher implements AppRequestMatcher {

    private String action;

    public AppActionMatcher(String action) {
        this.action = action;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return request.getRequestURI()
                .equals(
                        AppConstant.APP_URL_PATTERNS
                                .replace("*","")
                                .concat(action)
                );
    }
}
