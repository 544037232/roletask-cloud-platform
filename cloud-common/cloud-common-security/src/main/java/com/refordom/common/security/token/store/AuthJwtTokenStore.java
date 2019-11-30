package com.refordom.common.security.token.store;

import com.refordom.common.security.token.enhancer.DefaultJwtTokenEnhancer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器使用 JWT RSA 非对称加密令牌
 *
 * @author 王晟权
 * @date 2019/9/24 16:21
 */
public class AuthJwtTokenStore {

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("roletask");
        return converter;
    }

    @Resource
    private TokenEnhancerChain tokenEnhancerChain;

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
    @ConditionalOnMissingBean(TokenEnhancer.class)
    public TokenEnhancer jwtTokenEnhancer() {
        return new DefaultJwtTokenEnhancer();
    }
}
