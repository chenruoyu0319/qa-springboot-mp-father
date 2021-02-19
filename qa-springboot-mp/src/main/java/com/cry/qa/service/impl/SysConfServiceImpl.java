package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cry.qa.annotation.RedisCache;
import com.cry.qa.annotation.RedisCacheList;
import com.cry.qa.annotation.RedisCachePage;
import com.cry.qa.dao.SysConfDao;
import com.cry.qa.domain.SysConf;
import com.cry.qa.service.SysConfService;
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
public class SysConfServiceImpl implements SysConfService {

    @Autowired
    private SysConfDao sysConfDao;

    /**
     * 查询APP版本号
     *
     * @return
     */
    @Override
    public String getVersion() {
        return sysConfDao.getVersion();
    }

    /**
     * 查询APP下载次数
     *
     * @return
     */
    @Override
    public int getDownload() {
        return sysConfDao.getDownload();
    }

    /**
     * 下次次数 + 1
     */
    @Override
    public void addDownload() {
        sysConfDao.addDownload();
    }

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
    public List<SysConf> findPage(SysConf condition, Integer pageNum, Integer pageSize) {
        PageInfo<SysConf> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            sysConfDao.selectList(Wrappers.<SysConf>query(condition));
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
    public List<SysConf> find(SysConf condition) {
        return sysConfDao.selectList(Wrappers.query(condition));
    }

    /**
     * 添加
     *
     * @param sysConf
     * @return
     */
    @Override
    public Integer add(SysConf sysConf) {
        return sysConfDao.insert(sysConf);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public SysConf findById(String id) {
        return sysConfDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param sysConf
     * @return
     */
    @Override
    public Integer update(SysConf sysConf) {
        return sysConfDao.updateById(sysConf);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return sysConfDao.deleteById(id);
    }
}
