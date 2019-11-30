package com.refordom.common.security.properties;

/**
 * @author : 王晟权
 * @date : 2019/6/24 17:54
 */
public class OAuth2Properties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "roletask";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }
}
