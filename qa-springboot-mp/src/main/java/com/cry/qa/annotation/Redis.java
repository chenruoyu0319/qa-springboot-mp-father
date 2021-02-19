package com.cry.qa.annotation;

import com.cry.qa.constant.RedisReturnType;

import java.lang.annotation.*;

/**
 * redis缓存注解
 * @author ASUS
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Redis {

    /**
     * key的生成策略，支持表达式语言，表达式中多个值用"_"分隔
     * 不填写默认使用当前方法名
     * 例如       #name_#id
     * 最终生成SpEL表达式为     #name+'_'+#id
     */
    String field() default "";

    /**
     * JSON序列化的类型
     * @return
     */
    RedisReturnType returnType();

    /**
     * 默认缓存时间是一天 60*60*24
     * @return
     */
    int expire() default 86400;
}
