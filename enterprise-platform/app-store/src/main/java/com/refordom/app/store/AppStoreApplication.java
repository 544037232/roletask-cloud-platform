package com.refordom.app.store;

import com.refordom.common.security.annotation.ResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author pricess.wang
 * @date 2019/12/12 16:50
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.refordom.app.model.dao")
@ResourceServer
@ComponentScan("com.refordom.app.*")
public class AppStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppStoreApplication.class, args);
    }
}
