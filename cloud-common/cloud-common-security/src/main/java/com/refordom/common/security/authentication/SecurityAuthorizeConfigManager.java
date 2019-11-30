package com.refordom.common.security.authentication;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 默认的授权配置管理器
 *
 * @author 王晟权
 *
 */
@Component
public class SecurityAuthorizeConfigManager implements AuthorizeConfigManager {

	@Resource
	private List<AuthorizeConfigProvider> authorizeConfigProviders;

	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		boolean existAnyRequestConfig = false;
		String existAnyRequestConfigName = null;

		for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
			boolean currentIsAnyRequestConfig = authorizeConfigProvider.config(config);
			if (existAnyRequestConfig && currentIsAnyRequestConfig) {
				throw new RuntimeException("重复的anyRequest配置:" + existAnyRequestConfigName + ","
						+ authorizeConfigProvider.getClass().getSimpleName());
			} else if (currentIsAnyRequestConfig) {
				existAnyRequestConfig = true;
				existAnyRequestConfigName = authorizeConfigProvider.getClass().getSimpleName();
			}
		}

		if(!existAnyRequestConfig){
			config.anyRequest().authenticated();
		}
	}

}
