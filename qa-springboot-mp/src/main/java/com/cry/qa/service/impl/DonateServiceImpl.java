package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cry.qa.annotation.RedisCache;
import com.cry.qa.annotation.RedisCacheList;
import com.cry.qa.annotation.RedisCachePage;
import com.cry.qa.dao.DonateDao;
import com.cry.qa.domain.Donate;
import com.cry.qa.service.DonateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-18 22:46
 * @Modified By:
 */
@Transactional
@Service
public class DonateServiceImpl implements DonateService {

    @Autowired
    private DonateDao donateDao;

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
    public List<Donate> findPage(Donate condition, Integer pageNum, Integer pageSize) {
        PageInfo<Donate> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            donateDao.selectList(Wrappers.<Donate>query(condition));
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
    public List<Donate> find(Donate condition) {
        return donateDao.selectList(Wrappers.query(condition));
    }

    /**
     * 添加
     *
     * @param donate
     * @return
     */
    @Override
    public Integer add(Donate donate) {
        return donateDao.insert(donate);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public Donate findById(String id) {
        return donateDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param donate
     * @return
     */
    @Override
    public Integer update(Donate donate) {
        return donateDao.updateById(donate);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return donateDao.deleteById(id);
    }
}
