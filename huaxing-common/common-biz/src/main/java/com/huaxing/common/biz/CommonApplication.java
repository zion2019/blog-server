package com.huaxing.common.biz;

import com.huaxing.framework.feign.annotation.EnableBlogFeignClients;
import com.huaxing.resource.security.annotation.EnableSystemResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Zion
 * @Description 公共服务
 **/
@EnableSystemResourceServer
@EnableBlogFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
