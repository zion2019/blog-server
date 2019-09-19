package com.huaxing.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Slf4j
@MapperScan("com.huaxing.user.mapper")
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class UserApplication{

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
