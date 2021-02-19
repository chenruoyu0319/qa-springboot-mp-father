package com.cry.qa.annotation;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:39
 * @Modified By:
 */

import java.lang.annotation.*;

/**
 * 元注解 用来标识查询数据库的方法
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetTokenRedisCache {
    //    RedisCacheNamespace nameSpace();
}
