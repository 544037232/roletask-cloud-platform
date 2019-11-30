package com.refordom.common.security.token.store;

import com.refordom.common.security.token.enhancer.DefaultJwtTokenEnhancer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源服务器 TokenStore 配置类，使用 JWT RSA 非对称加密
 *
 * @author 王晟权
 * @date 2019/8/20 9:25
 */
public class RedisJwtTokenStore extends AuthRedisTokenStore {

    @Resource
    private TokenEnhancerChain tokenEnhancerChain;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("roletask");
        return converter;
    }

    @PostConstruct
    public void init(){
        List<TokenEnhancer> enhancers = new ArrayList<>(2);

        enhancers.add(jwtTokenEnhancer());
        enhancers.add(jwtAccessTokenConverter());

        tokenEnhancerChain.setTokenEnhancers(enhancers);
    }
    /**
     * jwt 生成token 定制化处理
     * 添加一些额外的用户信息到token里面
     *
     * @return TokenEnhancer
     */
    @Bean
    @ConditionalOnMissingBean(DefaultJwtTokenEnhancer.class)
    public TokenEnhancer jwtTokenEnhancer() {
        return new DefaultJwtTokenEnhancer();
    }
}
