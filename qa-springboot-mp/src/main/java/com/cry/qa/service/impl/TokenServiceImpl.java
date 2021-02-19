package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.qa.dao.TokenDao;
import com.cry.qa.domain.Token;
import com.cry.qa.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-27 19:00
 * @Modified By:
 */
@Transactional
@Service
public class TokenServiceImpl extends ServiceImpl<TokenDao, Token> implements IService<Token>, TokenService {

    @Autowired
    private TokenDao tokenDao;

    private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    /**
     * 持久化Token
     *
     * @param token
     * @return
     */
    @Override
    public Integer add(Token token) {
        return tokenDao.insert(token);
    }

}
