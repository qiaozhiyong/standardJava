package com.qiaozhy.standardjava.config.aspect;

import java.lang.annotation.*;

/**
 * @author: qiaozhy
 * @Description:
 * @Date: 2019/4/29 10:35 AM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebLog {

    String desc() default "" ;
}
