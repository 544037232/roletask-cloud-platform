package com.refordom.auth.handler;

import com.refordom.common.security.handler.AbstractAuthenticationFailureEvenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author 王晟权
 * @date 2019/0/8
 */
@Slf4j
@Component
public class SecurityAuthenticationFailureEvenHandler extends AbstractAuthenticationFailureEvenHandler {

	@Override
	public void handle(AuthenticationException authenticationException, Authentication authentication) {
		log.info("{} 认证失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());
	}
}
