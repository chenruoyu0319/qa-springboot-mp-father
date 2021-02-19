package com.cry.qa.redis;

import com.alibaba.fastjson.JSON;
import com.cry.qa.response.PageObject;
import com.cry.qa.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-23 15:42
 * @Modified By:
 */
@Component("redisCachePageObject")
public class RedisCachePageObject {

    //给类初始化日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCachePageObject.class);

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
    public Object getDataFromRedis(String redisKey) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(redisKey);
        if (value != null) {
/*            Object parse = JSON.parse(value);
            String s = parse.toString();*/
            return JSON.parseObject(value, PageObject.class);
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
        //设置key的过期时间 时间单位是秒(7天)
        jedis.expire(redisKey, 604800);
        return code;
    }

    /**
     * 删除redis中的数据
     *
     * @param key
     * @return
     */
    public Long delete(String key) {

        Jedis jedis = jedisPool.getResource();
        Long del = jedis.del(key);
        return del;
    }

    /**
     * 正则匹配所有符合条件的keys
     *
     * @param className
     * @return
     */
    public Set<String> getKeysByPattern(String className) {

        Jedis jedis = jedisPool.getResource();
        StringBuilder stringBuilder = new StringBuilder();
        // *QuestionServiceImpl*
        stringBuilder.append("*").append(className).append("*");
        Set<String> keys = jedis.keys(stringBuilder.toString());
        return keys;
    }
}