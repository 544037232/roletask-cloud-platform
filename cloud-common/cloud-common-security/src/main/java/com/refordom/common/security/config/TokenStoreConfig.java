package com.refordom.common.security.config;

import com.refordom.common.security.token.store.AuthJdbcTokenStore;
import com.refordom.common.security.token.store.AuthJwtTokenStore;
import com.refordom.common.security.token.store.AuthRedisTokenStore;
import com.refordom.common.security.token.store.RedisJwtTokenStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;

/**
 * <p>token存储</p>
 * 可选jdbc，jwt，redis，redis与jwt并存
 * 默认使用jwt与redis并存方式存储token
 *
 * @author pricess.wang
 * @date 2019/9/23 15:23
 */
@Configuration
public class TokenStoreConfig {

    @Configuration
    @ConditionalOnProperty(prefix = "auth.token.store", name = "type", havingValue = "jdbc")
    @Import(AuthJdbcTokenStore.class)
    public static class JdbcTokenConfig {
    }

    @Configuration
    @ConditionalOnProperty(prefix = "auth.token.store", name = "type", havingValue = "redis")
    @Import(AuthRedisTokenStore.class)
    public static class RedisTokenConfig {
    }

    @Configuration
    @ConditionalOnProperty(prefix = "auth.token.store", name = "type", havingValue = "jwt")
    @Import(AuthJwtTokenStore.class)
    public static class AuthJwtTokenConfig {
    }

    @Configuration
    @ConditionalOnProperty(prefix = "auth.token.store", name = "type", havingValue = "redisJwt", matchIfMissing = true)
    @Import(RedisJwtTokenStore.class)
    public static class ResJwtTokenConfig {
    }

    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        return new TokenEnhancerChain();
    }
}
