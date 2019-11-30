package com.refordom.auth.handler;

import com.refordom.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author 王晟权
 * @date 2019/9/8
 */
@Slf4j
@Component
public class SecurityAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	@Override
	public void handle(Authentication authentication) {
		log.info("认证成功");
	}
}
