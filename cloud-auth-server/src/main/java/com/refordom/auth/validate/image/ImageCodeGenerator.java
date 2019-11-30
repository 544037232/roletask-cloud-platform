package com.refordom.auth.validate.image;

import cn.hutool.captcha.CircleCaptcha;
import com.refordom.auth.properties.ValidateCodeProperties;
import com.refordom.auth.validate.ValidateCodeGenerator;
import com.refordom.auth.validate.code.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * 图形验证码生成器
 *
 * @author : 王晟权
 * @date : 2019/6/12 14:46
 */
@Component("imageValidateCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator<ImageCode> {

    @Resource
    private ValidateCodeProperties codeProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {

        CircleCaptcha circleCaptcha = new CircleCaptcha(codeProperties.getImage().getWidth(),
                codeProperties.getImage().getHeight(),
                codeProperties.getImage().getLength(), 0);

        return new ImageCode(circleCaptcha, codeProperties.getImage().getExpireIn());
    }
}
