package com.refordom.auth.config;

import com.refordom.auth.exception.AuthWebResponseExceptionTranslator;
import com.refordom.auth.service.UserClientDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author : 王晟权
 * @date : 2019/6/19 11:29
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private TokenEnhancerChain tokenEnhancerChain;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .exceptionTranslator(new AuthWebResponseExceptionTranslator())
                .pathMapping("/oauth/confirm_access", "/token/confirm_access");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new UserClientDetailsService());
    }

}
