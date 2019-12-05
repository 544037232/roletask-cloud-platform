package com.refordom.common.security.authentication;

import com.refordom.common.security.constant.SecurityConstants;
import com.refordom.common.security.properties.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 *
 * @author 王晟权
 */
@Component
@Order
public class SecurityAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstants.DEFAULT_AUTH_LOGIN_PAGE_ACCESS_TOKEN,
                SecurityConstants.DEFAULT_AUTH_LOGIN_FORM_ACCESS_TOKEN,
                SecurityConstants.DEFAULT_AUTH_LOGIN_MOBILE_ACCESS_TOKEN,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                SecurityConstants.OAUTH + "/**",
                securityProperties.getBrowser().getLoginSignIn(),
                securityProperties.getBrowser().getLoginSignUp(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl()).permitAll();

        return false;
    }

}
