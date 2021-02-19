package com.cry.qa.service.impl;

import com.cry.qa.annotation.GetTokenRedisCache;
import com.cry.qa.service.LoginService;
import com.cry.qa.utils.Const;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

/**
 * 从Redis缓存中读取用户信息
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private HttpServletRequest request;

    // 用注解解耦Redis缓存
/*    @Autowired
    private CacheService cacheService;*/

    /**
     * 从Redis缓存中读取存储在Token中的用户信息,映射成UserViewModel
     *
     * @return
     */
    @GetTokenRedisCache
    @Override
    public Object getUserViewModel() {

        return request.getHeader(Const.TOKEN);
    }
}
