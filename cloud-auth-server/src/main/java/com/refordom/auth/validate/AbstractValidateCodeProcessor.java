package com.refordom.auth.validate;

import com.refordom.auth.validate.code.ValidateCode;
import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.exception.ValidateCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author : 王晟权
 * @date : 2019/6/12 14:22
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private Map<String, ValidateCodeGenerator<C>> validateCodeGenerators;

    @Resource
    private ValidateCodeRepository<C> validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);

        validateCode.setKey(buildKey(request));

        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 保存校验码
     *
     * @param request      请求
     * @param validateCode 校验码
     */
    private void save(ServletWebRequest request, C validateCode) {
        validateCodeRepository.save(request, validateCode);
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request      请求
     * @param validateCode 生成的校验码
     * @throws Exception 异常
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 构建校验的唯一key，如果是图形验证码，则使用随机时间戳作为唯一key，如果是手机验证码，则使用手机号作为唯一key
     */
    protected abstract String buildKey(ServletWebRequest servletWebRequest) throws ServletRequestBindingException;

    @Override
    public void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ValidateCodeType codeType = getValidateCodeType();

        String key = buildKey(servletWebRequest);

        if (null == key) {
            throw new ValidateCodeException("缺少登录必须的随机数参数:" + SecurityConstants.DEFAULT_VALIDATE_PARAMETER_NAME_KEY_RANDOM);
        }
        C codeInSession = validateCodeRepository.get(servletWebRequest, key);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),
                    SecurityConstants.DEFAULT_VALIDATE_CODE_PARAMETER);
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCodeException(codeType + "请填写验证码");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            validateCodeRepository.remove(servletWebRequest, key);
            throw new ValidateCodeException(codeType + "验证码已过期，请重新获取");
        }

        if (!codeInSession.getCode().equals(codeInRequest)) {
            throw new ValidateCodeException(codeType + "验证码不正确");
        }

        validateCodeRepository.remove(servletWebRequest, key);
    }

    private C generate(ServletWebRequest request) throws Exception {
        String type = getValidateCodeType().toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator<C> validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return validateCodeGenerator.generate(request);
    }

    /**
     * 根据请求的url获取校验码的类型
     *
     * @return 执行器类型
     */
    private ValidateCodeType getValidateCodeType() {
        String type = getClass().getSimpleName().substring(0, getClass().getSimpleName().indexOf("CodeProcessor"));
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

}
