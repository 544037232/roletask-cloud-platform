package com.refordom.auth.authentication;

import com.refordom.common.security.authentication.SecurityUserDetailsService;
import com.refordom.common.security.token.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 短信登录验证逻辑
 *
 * 由于短信验证码的验证在过滤器里已完成，这里直接读取用户信息即可。
 *
 * @author 王晟权
 *
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private SecurityUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

		UserDetails user = userDetailsService.loadUserByMobile((String) authenticationToken.getPrincipal());

		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}

		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());

		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setUserDetailsService(SecurityUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
