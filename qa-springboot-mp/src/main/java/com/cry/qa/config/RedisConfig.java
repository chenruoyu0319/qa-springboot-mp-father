package com.cry.qa.config;

import com.cry.qa.aspect.RedisAspectPageObject;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-02-18 15:47
 * @Modified By:
 */
@Configuration
// 指定配置文件路径
@PropertySource("classpath:redis.properties")
public class RedisConfig {

    @Value("${redis.pointcut}")
    private String pointcut;

    public String getPointcut() {
        return this.pointcut;
    }

}
