package com.huaxing.gateway.configuration.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zion
 * @Description swagger配置
 **/
@Data
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";

    /**
     * 需要排除的服务
     */
    private List<String> ignoreProviders = new ArrayList<>();
}
