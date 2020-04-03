package com.refordom.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : 王晟权
 * @date : 2019/6/11 19:32
 */
@ConfigurationProperties(prefix = "auth")
public class SecurityProperties {

    /**
     * 社交配置
     */
    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }
}
