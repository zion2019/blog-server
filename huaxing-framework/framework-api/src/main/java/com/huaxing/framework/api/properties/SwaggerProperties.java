package com.huaxing.framework.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Zion
 * @Description 各服务模块属性
 **/
@Data
@Component
@ConfigurationProperties("huaxing.swagger")
public class SwaggerProperties {

    private String basePackage = "com.huaxing";

    private String title = "服务名";

    private String description = "文档描述";

}
