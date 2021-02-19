package com.cry.qa.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-02-02 21:28
 * @Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,METHOD})
@Documented
public @interface ResponseResult {
}
