package com.refordom.auth.config;

import com.refordom.auth.properties.ValidateCodeProperties;
import com.refordom.auth.validate.ValidateCodeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.annotation.Resource;

/**
 * 校验码相关安全配置
 *
 * @author 王晟权
 */
@Configuration
@EnableConfigurationProperties(ValidateCodeProperties.class)
public class ValidateSecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterAfter(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }

}
