package com.refordom.common.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 2.*版本相对1.5*版本不在默认加载ProviderManager，所以需要默认注入一个
 *
 * @author : 王晟权
 * @date : 2019/6/21 16:11
 */
@Order(101)
public class AuthenticationManagerConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    @ConditionalOnMissingBean(AuthenticationManager.class)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
