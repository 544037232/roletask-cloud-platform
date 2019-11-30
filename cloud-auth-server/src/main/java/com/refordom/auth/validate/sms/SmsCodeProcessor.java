package com.refordom.auth.validate.sms;

import com.refordom.auth.validate.AbstractValidateCodeProcessor;
import com.refordom.common.security.constant.SecurityConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 图片验证码发送器
 *
 * @author : 王晟权
 * @date : 2019/6/12 14:50
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {

    @Override
    protected void send(ServletWebRequest request, SmsCode smsCode) {
        logger.info("此处调用发送短信推送接口,发送的手机号是:{},生成的验证码是:{}", smsCode.getMobile(), smsCode.getCode());
    }

    @Override
    protected String buildKey(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        return ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
    }

}
