package com.refordom.common.security.config;

import com.refordom.common.security.properties.AuthRequestFactory;
import com.refordom.common.security.properties.SecurityProperties;
import me.zhyd.oauth.cache.AuthDefaultStateCache;
import me.zhyd.oauth.cache.AuthStateCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author : 王晟权
 * @date : 2019/6/11 19:35
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@ComponentScan("com.refordom.common.security.**")
public class SecurityCoreConfiguration {

    /**
     * 第三方认证配置
     */
    @Bean
    public AuthRequestFactory authRequestFactory(SecurityProperties properties, AuthStateCache authStateCache) {
        return new AuthRequestFactory(properties, authStateCache);
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthStateCache authStateCache() {
        return AuthDefaultStateCache.INSTANCE;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:security/messages_zh_CN");
        return source;
    }

}
