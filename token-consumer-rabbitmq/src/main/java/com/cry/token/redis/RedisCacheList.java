package com.cry.token.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:42
 * @Modified By:
 */
@Component("redisCacheList")
public class RedisCacheList {

    @Autowired
    private JedisPool jedisPool;

    private JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 从Redis缓存获取数据List
     *
     * @param redisKey
     * @return
     */
    public List<Object> getListFromRedis(String redisKey, Class beanType) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(redisKey);
        System.out.println(value);
        if (value != null) {
            Object parse = JSON.parse(value);
            if (parse != null){
                String s = parse.toString();
                //"[{'password':'123123','username':'zhangsan'},{'password':'321321','username':'lisi'}]";
                System.out.println("json字符串转List<Object>对象:" + JSON.parseArray(s, beanType).toString());
                return JSON.parseArray(s, beanType);
            }
            return null;
        }
        return null;
    }

    /**
     * 保存List数据到Redis
     *
     * @param redisKey
     */
    public String saveListToRedis(String redisKey, List list) {

        Jedis jedis = jedisPool.getResource();
        String code = jedis.set(redisKey, JSON.toJSONString(list));
        System.out.println("List<Object>转json字符串:" + JSON.toJSONString(list));
        return code;
    }
}