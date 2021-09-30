package com.huaxing.blog.biz.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface TempAuthControl {
    String value() default "";
}
