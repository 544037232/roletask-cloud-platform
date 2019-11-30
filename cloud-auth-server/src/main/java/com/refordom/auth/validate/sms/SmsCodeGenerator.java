package com.refordom.auth.validate.sms;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.refordom.auth.properties.ValidateCodeProperties;
import com.refordom.auth.validate.ValidateCodeGenerator;
import com.refordom.auth.validate.ValidateCodeRepository;
import com.refordom.auth.validate.code.ValidateCode;
import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.exception.ValidateCodeException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 短信验证码生成器
 *
 * @author 王晟权
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator<SmsCode> {

    @Resource
    private ValidateCodeProperties codeProperties;

    @Resource
    private ValidateCodeRepository<SmsCode> validateCodeRepository;

    private static final String MOBIL_REGEX = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    @Override
    public SmsCode generate(ServletWebRequest request) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);

        if (StrUtil.isBlank(mobile)) {
            throw new ValidateCodeException("请输入手机号码");
        }

        if (mobile.length() != 11) {
            throw new ValidateCodeException("请输入11位手机号码");
        }

        if (!ReUtil.isMatch(MOBIL_REGEX, mobile)) {
            throw new ValidateCodeException("请输入正确的手机号码");
        }

        ValidateCode smsCode = validateCodeRepository.get(request, mobile);

        if (null != smsCode) {
            Duration duration = Duration.between(LocalDateTime.now(), smsCode.getExpireTime());

            if (duration.toMillis() > 0 && duration.toMillis() < (60 * 1000)) {
                throw new ValidateCodeException("操作过于频繁,请" + (duration.toMillis() / 1000) + "秒后再试.");
            }
        }
        String code = RandomUtil.randomNumbers(codeProperties.getSms().getLength());

        return new SmsCode(mobile, code, codeProperties.getSms().getExpireIn());
    }

}
