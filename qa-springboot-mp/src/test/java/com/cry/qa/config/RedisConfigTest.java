package com.cry.qa.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-02-18 15:58
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    /**
     * 注入自定义配置类
     */
    @Autowired
    RedisConfig redisConfig;

    @Test
    public void testGetPointcut() {

        String POINTCUT = redisConfig.getPointcut();
        System.out.println(POINTCUT);
    }
}