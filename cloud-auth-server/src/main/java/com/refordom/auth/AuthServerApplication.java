package com.refordom.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>认证服务器</p>
 *
 * @author pricess.wang
 * @date 2019/9/16 16:30
 */

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan("com.refordom.auth.dao")
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
