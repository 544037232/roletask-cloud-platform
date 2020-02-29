package com.refordom.common.security.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码存取器
 *
 * @author zhailiang
 */
public interface ValidateCodeRepository<C extends ValidateCode> {

    /**
     * 保存验证码
     *
     * @param request 请求
     * @param code    校验码
     */
    void save(ServletWebRequest request, C code);

    /**
     * 获取验证码
     *
     * @param request 请求
     * @param key     校验的key
     * @return ValidateCode
     */
    C get(ServletWebRequest request, String key);

    /**
     * 移除验证码
     *
     * @param request 请求
     * @param key     校验的key
     */
    void remove(ServletWebRequest request, String key);

}
