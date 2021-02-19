package com.cry.token.service;


import com.cry.token.domain.Token;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-27 18:57
 * @Modified By:
 */
public interface TokenService {

    /**
     * 持久化Token
     *
     * @param token
     * @return
     */
    Integer add(Token token);
}
