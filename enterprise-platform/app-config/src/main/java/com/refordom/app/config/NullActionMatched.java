package com.refordom.app.config;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/31 17:09
 */
public class NullActionMatched implements AppRequestMatcher {

    @Override
    public boolean matches(HttpServletRequest request) {
        return false;
    }
}
