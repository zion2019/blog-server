package com.huaxing.framework.core.jackson.util;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @Title: ClassUtils.java
 * @Package com.learn.jackson.common.util
 * @Description: TODO
 * @author dongzhi.Wang
 * @date Creation time: 2019年5月10日
 * @version V1.0
 */
public class ClassUtils {

    public static Field[] getAllField(Class<?> clazz) {
        List<Field> respoisty = new ArrayList<Field>();
        List<Field> fields = getField(clazz, respoisty);
        return fields.toArray(new Field[fields.size()]);
    }

    private static List<Field> getField(Class<?> clazz,List<Field> respoisty) {
        if(clazz != null) {
            Collections.addAll(respoisty, clazz.getDeclaredFields());
            return getField(clazz.getSuperclass(),respoisty);
        }
        return respoisty;
    }

}

