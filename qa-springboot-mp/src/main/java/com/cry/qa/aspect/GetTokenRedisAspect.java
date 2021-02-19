package com.cry.qa.aspect;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:50
 * @Modified By:
 */

import com.cry.qa.redis.RedisCache;
import com.cry.qa.response.UserViewModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * AOP实现Redis缓存处理
 */
@Component
@Aspect
public class GetTokenRedisAspect {

    //给类初始化日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(GetTokenRedisAspect.class);

    @Autowired
    @Qualifier("redisCache")
    private RedisCache redisCache;

    /**
     * 拦截所有元注解TokenRedisCache注解的方法
     */
    @Pointcut("@annotation(com.cry.qa.annotation.GetTokenRedisCache)||@within(com.cry.qa.annotation.GetTokenRedisCache)")
    public void pointcutMethod() {

    }

    /**
     * 环绕处理,从执行切点方法,再创建Token令牌放入Redis里
     * 然后再保存到Redis缓存里
     *
     * @param joinPoint
     * @return
     */
    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) {

        String token = null;
        // 执行切点方法,获取请求头中的{X-TOKEN: tokenValue}
        try {
            token = (String) joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // 从Redis缓存取出UserViewModel对象用作身份校验
        UserViewModel userViewModel = null;
        if (token != null && !token.equals("")) {
            long startTime = System.currentTimeMillis();
            userViewModel = (UserViewModel) redisCache.getDataFromRedis(token, UserViewModel.class);
            long endTime = System.currentTimeMillis();
            LOGGER.info("Redis缓存AOP处理所用时间:" + (endTime - startTime));
        } else {
            LOGGER.info("**********没有从RequestHeader中查到Token**********");
        }

        if (userViewModel != null) {
            LOGGER.info("**********从Redis中查到了Token**********");
            LOGGER.info("Redis的KEY值:" + token);
            LOGGER.info("Redis的VALUE值:" + userViewModel.toString());
            return userViewModel;
        }

        LOGGER.info("**********没有从Redis中查到Token**********");
        return null;
    }
}
