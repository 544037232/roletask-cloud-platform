package com.refordom.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : 王晟权
 * @date : 2019/6/11 19:32
 */
@ConfigurationProperties(prefix = "auth")
public class SecurityProperties {

    /**
     * 网站配置项
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 社交配置
     */
    private SocialProperties social = new SocialProperties();

    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();

    public SocialProperties getSocial() {
        return social;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public OAuth2Properties getOauth2() {
        return oauth2;
    }
}
