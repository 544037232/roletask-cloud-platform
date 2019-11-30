package com.refordom.auth.validate.sms;

import com.refordom.auth.validate.code.ValidateCode;

import java.time.LocalDateTime;

/**
 * 手机验证码
 *
 * @author : 王晟权
 * @date : 2019/6/12 14:54
 */
public class SmsCode extends ValidateCode {

    /**
     * 手机号
     */
    private String mobile;

    public SmsCode(String mobile, String code, int expireIn) {
        super(code, LocalDateTime.now().plusSeconds(expireIn));
        this.mobile= mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
