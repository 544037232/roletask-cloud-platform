package com.refordom.common.security.config;

import com.refordom.common.security.constant.SecurityConstants;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : 王晟权
 * @date : 2019/6/6 16:18
 */
@Component
public class LoginAuthenticationConfiguration  {

    @Resource
    private AuthenticationFailureHandler securityAuthenticationFailHandler;

    @Resource
    private AuthenticationSuccessHandler loginFormAuthenticationSuccessHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_SIGN_IN_LOGIN_PAGE)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_LOGIN_FORM)
                .failureHandler(securityAuthenticationFailHandler)
                .successHandler(loginFormAuthenticationSuccessHandler);
    }
}
