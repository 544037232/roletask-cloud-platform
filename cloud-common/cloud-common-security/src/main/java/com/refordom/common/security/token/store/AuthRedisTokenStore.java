package com.refordom.common.security.token.store;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * 认证服务器使用Redis存取令牌
 * 注意: 需要配置redis参数
 *
 * @author 王晟权
 * @date 2019/8/25 9:36
 */
public class AuthRedisTokenStore {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
}
