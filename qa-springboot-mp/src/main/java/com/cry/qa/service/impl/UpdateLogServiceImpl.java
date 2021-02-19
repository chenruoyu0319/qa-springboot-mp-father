package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cry.qa.annotation.RedisCache;
import com.cry.qa.annotation.RedisCacheList;
import com.cry.qa.dao.UpdateLogDao;
import com.cry.qa.domain.UpdateLog;
import com.cry.qa.service.UpdateLogService;
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
public class UpdateLogServiceImpl implements UpdateLogService {

    @Autowired
    private UpdateLogDao updateLogDao;

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RedisCacheList
    @Override
    public List<UpdateLog> findPage(UpdateLog condition, Integer pageNum, Integer pageSize) {
        PageInfo<UpdateLog> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            updateLogDao.selectList(Wrappers.<UpdateLog>query(condition));
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
    public List<UpdateLog> find(UpdateLog condition) {
        return updateLogDao.selectList(Wrappers.query(condition));
    }

    /**
     * 添加
     *
     * @param updateLog
     * @return
     */
    @Override
    public Integer add(UpdateLog updateLog) {
        return updateLogDao.insert(updateLog);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public UpdateLog findById(String id) {
        return updateLogDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param updateLog
     * @return
     */
    @Override
    public Integer update(UpdateLog updateLog) {
        return updateLogDao.updateById(updateLog);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return updateLogDao.deleteById(id);
    }
}
