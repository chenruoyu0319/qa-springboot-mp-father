package com.cry.qa.aspect;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:50
 * @Modified By:
 */

import com.cry.qa.redis.RedisCachePageObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * AOP实现Redis缓存处理
 */
@Component
@Aspect
public class DeleteRedisAspectPageObject {

    //给类初始化日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteRedisAspectPageObject.class);

    @Autowired
    @Qualifier("redisCachePageObject")
    private RedisCachePageObject redisCachePageObject;

    /**
     * 拦截所有元注解RedisCache注解的方法
     */
    // TODO: 避免写死
    @Pointcut("@annotation(com.cry.qa.annotation.DeleteRedisCachePageObject)||@within(com.cry.qa.annotation.DeleteRedisCachePageObject)")
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
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        //前置
        long startTime = System.currentTimeMillis();
        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        //用正则定位keys, 批量删除Redis里获取缓存;
        //redis中key格式: 参数名:类名.方法名 => 定位: QuestionServiceImpl(ClassName)
        Set<String> redisKeys = redisCachePageObject.getKeysByPattern(className);

        int i = 1;
        if (redisKeys != null && !redisKeys.isEmpty()) {
            LOGGER.info("**********Redis中有过期数据**********");
            LOGGER.info("**********开始删除过期数据**********");
            for (String redisKey : redisKeys) {
                Long delete = redisCachePageObject.delete(redisKey);
                LOGGER.info("**********已删除过期数据: " + i++ + "**********");
            }
        }
        LOGGER.info("**********Redis中已没有过期数据**********");
        long endTime = System.currentTimeMillis();
        LOGGER.info("Redis缓存AOP删除处理所用时间:" + (endTime - startTime));
        Object proceed = joinPoint.proceed();

        // 延时双删, 保证最终一致性
        Thread.sleep(1000);
        Set<String> doubleRedisKeys = redisCachePageObject.getKeysByPattern(className);
        int j = 1;
        if (redisKeys != null && !doubleRedisKeys.isEmpty()) {
            LOGGER.info("**********Redis中又有过期脏数据**********");
            LOGGER.info("**********开始删除过期脏数据**********");
            for (String redisKey : doubleRedisKeys) {
                Long delete = redisCachePageObject.delete(redisKey);
                LOGGER.info("**********已删除脏过期数据: " + j++ + "**********");
            }
        }
        LOGGER.info("**********二次检查Redis中已没有过期数据**********");
        return proceed;
    }
}
