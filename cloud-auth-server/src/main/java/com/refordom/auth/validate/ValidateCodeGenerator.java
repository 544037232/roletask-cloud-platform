package com.refordom.auth.validate;

import com.refordom.auth.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成接口
 *
 * @author : 王晟权
 * @date : 2019/6/12 14:13
 */
public interface ValidateCodeGenerator<C extends ValidateCode> {

    /**
     * 生成校验码
     *
     * @param request 请求
     * @throws  Exception 异常
     * @return validateCode
     */
    C generate(ServletWebRequest request) throws Exception;
}
