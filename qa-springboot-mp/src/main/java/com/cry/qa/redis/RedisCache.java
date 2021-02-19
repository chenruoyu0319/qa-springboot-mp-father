package com.cry.qa.redis;

import com.alibaba.fastjson.JSON;
import com.cry.qa.aspect.RedisAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //给类初始化日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

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
    public Object getDataFromRedis(String redisKey, Class beanType) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(redisKey);
        if (value != null) {
            Object parse = JSON.parse(value);
            String s = parse.toString();
            return JSON.parseObject(s, beanType);
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


    /**
     * 删除数据
     *
     * @param key：要删除数据的key
     * @return：返回boolean值，表示是否删除成功
     */
    public boolean delete(String key) {

        Jedis jedis = jedisPool.getResource();
        Boolean exist = jedis.exists(key);
        if (exist) {
            Long del = jedis.del(key);
            if (del == 1) {
                LOGGER.info("删除数据成功");
                return true;
            } else {
                LOGGER.info("删除数据失败");
                return false;
            }
        } else {
            LOGGER.info(key + "不存在");
            return false;
        }
    }
}