package com.refordom.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 验证码配置项
 * @author : 王晟权
 * @date : 2019/6/12 11:35
 */
@ConfigurationProperties(prefix = "auth.validate")
public class ValidateCodeProperties {

    /**
     * 图片验证码配置项
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 手机号验证码配置项
     */
    private SmsCodeProperties sms = new SmsCodeProperties();

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
