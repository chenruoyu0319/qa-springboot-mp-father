package com.cry.qa.aspect;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:50
 * @Modified By:
 */

import com.cry.qa.config.RedisConfig;
import com.cry.qa.config.RedisConfig2;
import com.cry.qa.redis.RedisCache;
import com.cry.qa.redis.RedisCachePageObject;
import com.cry.qa.utils.ClassNameUtils;
import org.aopalliance.aop.Advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.cry.qa.config.RedisConfig2.CONFIG;

/**
 * AOP实现Redis缓存处理
 */
@Component
@Aspect
public class RedisAspectPageObject implements Advice {

    //给类初始化日志对象
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("redisCachePageObject")
    private RedisCachePageObject redisCachePageObject;

    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    @Pointcut("execution(* com.*.*(..))||@annotation(com.cry.qa.annotation.RedisCachePageObject)")
    public void pointcutMethod() {

    }

    /**
     * 环绕处理，先从Redis里获取缓存,查询不到，就查询MySQL数据库，
     * 然后再保存到Redis缓存里
     *
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws ClassNotFoundException {
        //前置：从Redis里获取缓存
        //先获取目标方法参数
        long startTime = System.currentTimeMillis();
        String applId = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            applId = String.valueOf(args[0]);
        }

        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();

        //redis中key格式：    applId:方法名称
        String redisKey = applId + ":" + className + "." + methodName;

        Object obj = redisCachePageObject.getDataFromRedis(redisKey);

        if (obj != null) {
            LOGGER.info("**********从Redis中查到了数据**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + obj.toString());
            return obj;
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("Redis缓存AOP处理所用时间:" + (endTime - startTime));
        LOGGER.info("**********没有从Redis查到数据**********");
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        LOGGER.info("**********开始从MySQL查询数据**********");
        //后置：将数据库查到的数据保存到Redis
        String code = redisCachePageObject.saveDataToRedis(redisKey, obj);
        if (code.equals("OK")) {
            LOGGER.info("**********数据成功保存到Redis缓存!!!**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + obj.toString());
        }
        return obj;
    }
}
