package com.cry.qa.aspect;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:50
 * @Modified By:
 */

import com.cry.qa.redis.RedisCachePage;
import com.cry.qa.utils.ClassNameUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AOP实现Redis缓存处理
 */
@Component
@Aspect
public class RedisAspectPage {

    //给类初始化日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisAspectPage.class);

    @Autowired
    @Qualifier("redisCachePage")
    private RedisCachePage redisCachePage;

    /**
     * 拦截所有元注解RedisCachePage注解的方法
     */
    @Pointcut("@annotation(com.cry.qa.annotation.RedisCachePage)||@within(com.cry.qa.annotation.RedisCachePage)")
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
        StringBuilder applId = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        System.out.println(args.length);
        if (args != null && args.length > 0) {
            // 包括无参数
            applId.append(args[0])
                    .append(":")
                    .append(args[1])
                    .append(":")
                    .append(args[2]);
        }
        System.out.println("获取目标方法参数" + applId);

        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();

        //redis中key格式：    applId:方法名称
        String redisKey = applId + ":" + className + "." + methodName;

        List<Object> obj = redisCachePage.getListFromRedis(redisKey, ClassNameUtils.reflectClassName(className));
        System.out.println(ClassNameUtils.reflectClassName(className));

        if (obj != null) {
            LOGGER.info("**********从Redis中查到了List**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + obj.toString());
            return obj;
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("Redis缓存AOP处理所用时间:" + (endTime - startTime));
        LOGGER.info("**********没有从Redis查到List**********");
        List<Object> list = null;
        try {
            list = (List<Object>) joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        LOGGER.info("**********开始从MySQL查询List**********");
        //后置：将数据库查到的数据保存到Redis
        String code = redisCachePage.saveListToRedis(redisKey, list);
        if (code.equals("OK")) {
            LOGGER.info("**********List成功保存到Redis缓存!!!**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("REDIS的VALUE值:" + list.toString());
        }
        return list;
    }
}
