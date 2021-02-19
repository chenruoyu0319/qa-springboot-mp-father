package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cry.qa.annotation.RedisCache;
import com.cry.qa.annotation.RedisCacheList;
import com.cry.qa.annotation.RedisCachePage;
import com.cry.qa.dao.AnswerDao;
import com.cry.qa.domain.Answer;
import com.cry.qa.service.AnswerService;
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
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

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
    public List<Answer> findPage(Answer condition, Integer pageNum, Integer pageSize) {
        PageInfo<Answer> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            answerDao.selectList(Wrappers.query(condition));
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
    public List<Answer> find(Answer condition) {
        return answerDao.selectList(Wrappers.query(condition));
    }

    /**
     * 添加
     *
     * @param answer
     * @return
     */
    @Override
    public Integer add(Answer answer) {
        return answerDao.insert(answer);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */

    @RedisCache
    @Override
    public Answer findById(String id) {
        return answerDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param answer
     * @return
     */
    @Override
    public Integer update(Answer answer) {
        return answerDao.updateById(answer);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return answerDao.deleteById(id);
    }
}
