package com.refordom.common.security.properties;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 王晟权
 * @date : 2019/6/14 15:54
 */
public class SocialProperties {

    /**
     * JustAuth 配置
     */
    private Map<AuthDefaultSource, AuthConfig> type = new HashMap<>();

    public Map<AuthDefaultSource, AuthConfig> getType() {
        return type;
    }

}
