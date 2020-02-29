package com.refordom.auth.validate.code;

import cn.hutool.captcha.CircleCaptcha;
import com.refordom.common.security.validate.ValidateCode;

import java.time.LocalDateTime;

/**
 * @author : 王晟权
 * @date : 2019/6/12 10:26
 */
public class ImageCode extends ValidateCode {

    private CircleCaptcha circleCaptcha;

    public ImageCode(CircleCaptcha circleCaptcha, int expireIn) {
        super(circleCaptcha.getCode(),LocalDateTime.now().plusSeconds(expireIn));
        this.circleCaptcha = circleCaptcha;
    }

    public CircleCaptcha getCircleCaptcha() {
        return circleCaptcha;
    }

    public void setCircleCaptcha(CircleCaptcha circleCaptcha) {
        this.circleCaptcha = circleCaptcha;
    }

}
