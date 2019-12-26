package com.refordom.app.config.core;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pricess.wang
 * @date 2019/12/18 10:54
 */
public class StandardAppToken extends AbstractAppToken {

    public StandardAppToken(HttpServletRequest request) {
        super(request);
    }

}
