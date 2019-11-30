package com.refordom.common.security.token.store;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 认证服务器使用数据库存取令牌
 *
 * @author 王晟权
 * @date 2019/9/24 16:23
 */
public class AuthJdbcTokenStore {

    @Resource
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }
}
