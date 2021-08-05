package com.huaxing.resource.biz;

import com.huaxing.framework.feign.annotation.EnableBlogFeignClients;
import com.huaxing.resource.security.annotation.EnableSystemResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Zion
 * @Description system start class
 **/
@EnableSystemResourceServer
@EnableBlogFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
