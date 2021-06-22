package com.huaxing.framework.web.configuration;

import com.huaxing.framework.datasource.handler.MetaObjectUserSource;
import com.huaxing.resource.security.util.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * @author Zion
 * @Description 填充当前用户信息
 **/
@Component
public class CustomMetaObjectUserSource implements MetaObjectUserSource {

    @Override
    public Long getUserId() {
        return SecurityUtils.getUser().getId();
    }

    @Override
    public String getUserName() {
        return SecurityUtils.getUser().getUsername();
    }
}
