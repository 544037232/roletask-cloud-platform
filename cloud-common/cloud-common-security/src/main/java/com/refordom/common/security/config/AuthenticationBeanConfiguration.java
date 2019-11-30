package com.refordom.common.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * 认证相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 *
 * @author 王晟权
 */
@Configuration
public class AuthenticationBeanConfiguration {

    /**
     * 默认密码处理器
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 默认remember token存储器
     */
    @Bean
    @ConditionalOnMissingBean(PersistentTokenRepository.class)
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

	/**
	 * 默认的认证成功处理器
	 * @return SavedRequestAwareAuthenticationSuccessHandler
	 */
	@Bean
    @ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
    	return new SavedRequestAwareAuthenticationSuccessHandler();
    }

	@Bean
	@ConditionalOnMissingBean(AuthenticationFailureHandler.class)
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
}
