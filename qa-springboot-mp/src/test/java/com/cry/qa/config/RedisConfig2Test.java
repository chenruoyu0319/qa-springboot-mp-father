package com.cry.qa.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-02-19 1:05
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfig2Test {

    @Test
    public void testRedisConfig2(){
        System.out.println(RedisConfig2.CONFIG);
    }
}