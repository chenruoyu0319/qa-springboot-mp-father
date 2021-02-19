package com.cry.token.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:42
 * @Modified By:
 */
@Component("redisCache")
public class RedisCache {

    @Autowired
    private JedisPool jedisPool;

    private JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 从Redis缓存获取数据
     *
     * @param redisKey
     * @return
     */
    public Object getDataFromRedis(String redisKey,Class beanType) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(redisKey);
        if (value != null) {
            Object parse = JSON.parse(value);
            String s = parse.toString();
            return JSON.parseObject(s,beanType);
        }
        return null;
    }

    /**
     * 保存数据到Redis
     *
     * @param redisKey
     */
    public String saveDataToRedis(String redisKey, Object obj) {

        Jedis jedis = jedisPool.getResource();
        String code = jedis.set(redisKey, JSON.toJSONString(obj));
        return code;
    }
}