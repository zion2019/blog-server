package com.huaxing.site.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Zion
 * @Description system start class
 **/
@SpringBootApplication(scanBasePackages = {"com.huaxing.framework","com.huaxing.site"})
@EnableEurekaClient
@MapperScan("com.huaxing.site.system.mapper.**")
@EntityScan("com.huaxing.site.system.entity.**")
@EnableTransactionManagement(proxyTargetClass=true)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
