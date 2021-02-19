package com.cry.qa.service;

import com.cry.qa.domain.SysConf;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface SysConfService {

    /**
     * 查询APP版本号
     * @return
     */
    public String getVersion();

    /**
     * 查询APP下载次数
     * @return
     */
    public int getDownload();

    /**
     * 下次次数 + 1
     */
    public void addDownload();

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<SysConf> findPage(SysConf condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<SysConf> find(SysConf condition);

    /**
     * 添加
     *
     * @param SysConf
     * @return
     */
    public Integer add(SysConf SysConf);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public SysConf findById(String id);

    /**
     * 修改
     *
     * @param SysConf
     * @return
     */
    public Integer update(SysConf SysConf);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
