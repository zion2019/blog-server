package com.huaxing.site.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zion
 * @Description 网关服务
 **/
@ComponentScan(basePackages = {"com.huaxing.site"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.huaxing.site.mesosphere.feign")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

