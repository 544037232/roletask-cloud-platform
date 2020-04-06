package com.refordom.dev.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author pricess.wang
 * @date 2019/12/3 11:50
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.refordom.dev.app.*.dao")
public class DevAppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevAppServerApplication.class, args);
    }
}
