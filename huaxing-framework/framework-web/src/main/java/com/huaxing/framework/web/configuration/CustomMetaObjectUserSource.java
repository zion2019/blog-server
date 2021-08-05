package com.huaxing.framework.web.configuration;

import com.huaxing.framework.datasource.handler.MetaObjectUserSource;
import com.huaxing.resource.security.service.SysUser;
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
        SysUser user = SecurityUtils.getUser();
        if(user != null){
            return user.getId();
        }
        return 1L;
    }

    @Override
    public String getUserName() {
        SysUser user = SecurityUtils.getUser();
        if(user != null){
            return user.getUsername();
        }
        return "system";
    }
}
