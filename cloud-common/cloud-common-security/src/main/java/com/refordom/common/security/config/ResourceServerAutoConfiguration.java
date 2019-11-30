package com.refordom.common.security.config;

import com.refordom.common.security.annotation.ResourceServer;
import com.refordom.common.security.authentication.AuthenticationAccessDeniedHandler;
import com.refordom.common.security.authentication.AuthenticationExceptionEntryPoint;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;

/**
 * <p>资源服务配置，需开启{@link ResourceServer}注解</p>
 *
 * @author pricess.wang
 * @date 2019/10/10 18:50
 */
public class ResourceServerAutoConfiguration extends ResourceServerConfigurerAdapter {

    @Resource
    private AuthenticationExceptionEntryPoint authenticationExceptionEntryPoint;

    @Resource
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(authenticationExceptionEntryPoint)
                .accessDeniedHandler(authenticationAccessDeniedHandler);
    }

}
