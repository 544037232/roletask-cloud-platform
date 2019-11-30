package com.refordom.auth.properties;

/**
 * 手机验证码配置
 * @author : 王晟权
 * @date : 2019/6/12 14:58
 */
public class SmsCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 过期时间
     */
    private int expireIn = 60;
    /**
     * 要拦截的url，多个url用逗号隔开，ant pattern
     */
    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int lenght) {
        this.length = lenght;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
