package com.cry.qa.annotation;

import java.lang.annotation.*;

/**
 * redis缓存注解
 * @author ASUS
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisEvict {

}
