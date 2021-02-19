package com.cry.token.aspect;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:50
 * @Modified By:
 */

import com.cry.token.domain.User;
import com.cry.token.redis.RedisCache;
import com.cry.token.response.UserViewModel;
import com.cry.token.utils.UuidUtil;
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
public class SetTokenRedisAspect {

    //给类初始化日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(SetTokenRedisAspect.class);

    @Autowired
    @Qualifier("redisCache")
    private RedisCache redisCache;

    /**
     * 拦截所有元注解TokenRedisCache注解的方法
     */
    @Pointcut("@annotation(com.cry.token.annotation.SetTokenRedisCache)||@within(com.cry.token.annotation.SetTokenRedisCache)")
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

        Object obj = null;
        // 执行切点方法
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // 生成一个32位不带"-"的UUID, 作为token
        String token = UuidUtil.getUUID();

        //redis中key格式: token
        String redisKey = token;

        // User -> UserViewModel
        UserViewModel loginUser = UserViewModel.userFrom((User) obj);

        // 存入Redis: {token: loginUser}
        String code = redisCache.saveDataToRedis(token, loginUser);

        if ("OK".equals(code)) {
            LOGGER.info("**********Token成功保存到Redis缓存!!!**********");
            LOGGER.info("Redis的KEY值:" + redisKey);
            LOGGER.info("Redis的VALUE值:" + loginUser.toString());
        }

        // 返回生成的token
        return token;
    }
}
