package com.huaxing.framework.core.jackson.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(value = { ElementType.METHOD,FIELD})
@Repeatable(value=JacksonFilters.class)
public @interface JacksonFilter {

    Class<?> value();

    /**
     * include为对象需要包含的字段，默认使用include，如果include为空，则使用exclude字段
     *
     * @Title: include
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param
     * @return String[]
     */
    String[] include() default {};

    /**
     * exclude 要排除的字段
     *
     * @Title: exclude
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param
     * @return String[]
     */
    String[] exclude() default {};


    JscksonFilterType type() default  JscksonFilterType.RESPONSE;

    enum JscksonFilterType {
        REQUEST, RESPONSE;
    }

}

