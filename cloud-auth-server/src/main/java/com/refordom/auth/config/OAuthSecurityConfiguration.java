package com.refordom.auth.config;

import com.refordom.auth.authentication.SmsCodeAuthenticationSecurityConfiguration;
import com.refordom.common.security.authentication.AuthorizeConfigManager;
import com.refordom.common.security.authentication.SecurityUserDetailsService;
import com.refordom.common.security.config.LoginAuthenticationConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * <p>web相关安全配置</p>
 *
 * @author pricess.wang
 * @date 2019/9/16 17:09
 */
@Configuration
@EnableWebSecurity
public class OAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private SecurityUserDetailsService securityUserDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthorizeConfigManager authorizeConfigManager;

    @Resource
    private ValidateSecurityConfiguration validateSecurityConfiguration;

    @Resource
    private SmsCodeAuthenticationSecurityConfiguration smsCodeAuthenticationSecurityConfiguration;

    @Resource
    private LoginAuthenticationConfiguration loginAuthenticationConfiguration;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        loginAuthenticationConfiguration.configure(http);

        http.apply(validateSecurityConfiguration)
                .and()
                .apply(smsCodeAuthenticationSecurityConfiguration)
                .and()
                .csrf().disable();// 关闭请求伪造

        authorizeConfigManager.config(http.authorizeRequests());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(AuthenticationManager.class)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
