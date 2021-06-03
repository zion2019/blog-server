package com.huaxing.framework.datasource.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Zion
 * @Description 数据拦截
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired(required = false)
    private MetaObjectUserSource metaObjectUserSource;

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        if(metaObjectUserSource != null){
            this.setFieldValByName("created_id", metaObjectUserSource.getUserId(), metaObject);
            this.setFieldValByName("created_name", metaObjectUserSource.getUserName(), metaObject);
        }
        this.setFieldValByName("createdTime", now, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        if(metaObjectUserSource != null){
            this.setFieldValByName("updated_id", metaObjectUserSource.getUserId(), metaObject);
            this.setFieldValByName("updated_name", metaObjectUserSource.getUserName(), metaObject);
        }
        this.setFieldValByName("updatedTime", now, metaObject);
    }
}
