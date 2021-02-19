package com.cry.qa.service;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-25 6:47
 * @Modified By:
 */
public interface LoginService {

    /**
     * 从Redis缓存中读取存储在Token中的用户信息,映射成UserViewModel
     *
     * @return
     */
    Object getUserViewModel();
}
