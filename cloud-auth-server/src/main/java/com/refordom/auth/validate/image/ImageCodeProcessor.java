package com.refordom.auth.validate.image;

import com.refordom.auth.validate.AbstractValidateCodeProcessor;
import com.refordom.auth.validate.code.ImageCode;
import com.refordom.common.security.constant.SecurityConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Objects;

/**
 * 图片验证码发送器
 * @author : 王晟权
 * @date : 2019/6/12 14:50
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        imageCode.getCircleCaptcha().write(Objects.requireNonNull(request.getResponse()).getOutputStream());
    }

    @Override
    protected String buildKey(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        return ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), SecurityConstants.DEFAULT_VALIDATE_PARAMETER_NAME_KEY_RANDOM);
    }

}
