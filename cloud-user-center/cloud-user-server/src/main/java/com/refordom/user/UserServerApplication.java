package com.refordom.user;

import com.refordom.common.security.annotation.ResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>用户服务</p>
 *
 * @author pricess.wang
 * @date 2019/12/7 11:03
 */
@EnableAsync
@MapperScan("com.refordom.user.dao")
@SpringBootApplication
@EnableDiscoveryClient
@ResourceServer
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class,args);
    }
}
