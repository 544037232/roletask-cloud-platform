package com.refordom.auth.validate;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 *
 * @author zhailiang
 *
 */
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 *
	 * @param request 请求
	 * @throws Exception 异常
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 *
	 * @param servletWebRequest  请求
	 */
	void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException;

}
