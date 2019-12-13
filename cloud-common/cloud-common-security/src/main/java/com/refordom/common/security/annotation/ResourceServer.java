package com.refordom.common.security.annotation;

import com.refordom.common.security.config.AuthenticationManagerConfiguration;
import com.refordom.common.security.config.ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * <p>资源服务，在服务中使用该注解，可对该服务资源进行验权</p>
 *
 * @author pricess.wang
 * @date 2019/10/10 18:47
 */
@Documented
@Inherited
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ResourceServerAutoConfiguration.class, AuthenticationManagerConfiguration.class})
public @interface ResourceServer {
}
