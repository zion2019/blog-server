package com.huaxing.framework.datasource.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Zion
 * @Description 数据拦截
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

//    @Autowired
//    private GlobalContext globalContext;

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
//        this.setFieldValByName("createdBy", globalContext.getCurrentUserId(), metaObject);
        this.setFieldValByName("createdTime", now, metaObject);
        this.setFieldValByName("deleted", 0, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
//        this.setFieldValByName("updatedBy",  globalContext.getCurrentUserId(), metaObject);
        this.setFieldValByName("updatedTime", now, metaObject);
    }
}
