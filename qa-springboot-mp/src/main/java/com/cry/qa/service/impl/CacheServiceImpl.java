package com.cry.qa.service.impl;

import com.alibaba.fastjson.JSON;
import com.cry.qa.response.UserViewModel;
import com.cry.qa.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 使用redis缓存登录信息
 * 存储在缓存名称为“token”中
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 创建Token,存储在Redis中
     * @param key
     * @param userViewModel
     * @return
     */
    @Override
    public String createToken(String key, UserViewModel userViewModel){
        Jedis jedis = jedisPool.getResource();
        String code = jedis.set(key, JSON.toJSONString(userViewModel));
        return code;
    }

    /**
     * 从Redis中取出Token
     * @param key
     * @return
     */
    @Override
    public UserViewModel getToken(String key){
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        if (value != null) {
            Object parse = JSON.parse(value);
            String s = parse.toString();
            return JSON.parseObject(s,UserViewModel.class);
        }
        return null;
    }

    /**
     * 从Redis中删除Token
     * @param key
     * @return
     */
    @Override
    public void deleteToken(String key){
    }

}