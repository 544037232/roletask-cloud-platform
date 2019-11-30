package com.refordom.common.security.properties;


import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.enums.LoginType;

/**
 * @author : 王晟权
 * @date : 2019/6/11 19:31
 */
public class BrowserProperties {

    private String loginSignIn = SecurityConstants.DEFAULT_SIGN_IN_LOGIN_URL;

    private String loginSignUp = SecurityConstants.DEFAULT_SIGN_IN_SIGN_UP_URL;

    private LoginType loginType = LoginType.JSON;

    /**
     * session配置
     */
    private SessionProperties session = new SessionProperties();

    public SessionProperties getSession() {
        return session;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginSignIn() {
        return loginSignIn;
    }

    public void setLoginSignIn(String loginSignIn) {
        this.loginSignIn = loginSignIn;
    }

    public String getLoginSignUp() {
        return loginSignUp;
    }

    public void setLoginSignUp(String loginSignUp) {
        this.loginSignUp = loginSignUp;
    }
}
