package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cry.qa.annotation.RedisCache;
import com.cry.qa.annotation.RedisCacheList;
import com.cry.qa.annotation.RedisCachePage;
import com.cry.qa.dao.TagDao;
import com.cry.qa.domain.Tag;
import com.cry.qa.service.TagService;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

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
    public List<Tag> findPage(Tag condition, Integer pageNum, Integer pageSize) {
        PageInfo<Tag> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            tagDao.selectList(Wrappers.<Tag>query(condition));
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
    public List<Tag> find(Tag condition) {
        return tagDao.selectList(Wrappers.query(condition));
    }

    /**
     * 添加
     *
     * @param tag
     * @return
     */
    @Override
    public Integer add(Tag tag) {
        return tagDao.insert(tag);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public Tag findById(String id) {
        return tagDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param tag
     * @return
     */
    @Override
    public Integer update(Tag tag) {
        return tagDao.updateById(tag);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return tagDao.deleteById(id);
    }
}
