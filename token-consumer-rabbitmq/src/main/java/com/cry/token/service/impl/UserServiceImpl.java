package com.cry.token.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.token.annotation.RedisCache;
import com.cry.token.annotation.RedisCacheList;
import com.cry.token.annotation.RedisCachePage;
import com.cry.token.dao.UserDao;
import com.cry.token.domain.User;
import com.cry.token.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-18 22:46
 * @Modified By:
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IService<User>, UserService {

    @Autowired
    private UserDao userDao;


    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RedisCachePage
    @Override
    public List<User> findPage(User condition, Integer pageNum, Integer pageSize) {
        PageInfo<User> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            userDao.selectList(Wrappers.query(condition));
        });
        return pageInfo.getList();
    }

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    @RedisCacheList
    @Override
    public List<User> find(User condition) {
        return userDao.selectList(Wrappers.query(condition));

    }

    /**
     * 添加
     *
     * @param user
     * @return
     */
    @Override
    public Integer add(User user) {
        return userDao.insert(user);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public User findById(String id) {
        return userDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @Override
    public Integer update(User user) {
        return userDao.updateById(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return userDao.deleteById(id);
    }
}
