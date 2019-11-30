package com.refordom.dev.user;

import com.refordom.common.security.annotation.ResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p> </p>
 *
 * @author pricess.wang
 * @date 2019/11/26 15:37
 */
@EnableAsync
@MapperScan("com.refordom.dev.user.dao")
@SpringBootApplication
@EnableDiscoveryClient
@ResourceServer
public class DevUserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevUserServerApplication.class, args);
    }
}
