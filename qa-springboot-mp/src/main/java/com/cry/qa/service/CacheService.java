package com.cry.qa.service;

import com.cry.qa.response.UserViewModel;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-25 6:57
 * @Modified By:
 */
public interface CacheService {

    /**
     * 创建Token,存储在Redis中
     * @param key
     * @param userViewModel
     * @return
     */
    public String createToken(String key, UserViewModel userViewModel);

    /**
     * 从Redis中取出Token
     * @param key
     * @return
     */
    public UserViewModel getToken(String key);

    /**
     * 从Redis中删除Token
     * @param key
     * @return
     */
    public void deleteToken(String key);
}
