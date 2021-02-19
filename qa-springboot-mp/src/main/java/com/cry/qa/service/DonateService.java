package com.cry.qa.service;

import com.cry.qa.domain.Donate;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface DonateService {

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Donate> findPage(Donate condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Donate> find(Donate condition);

    /**
     * 添加
     *
     * @param Donate
     * @return
     */
    public Integer add(Donate Donate);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public Donate findById(String id);

    /**
     * 修改
     *
     * @param Donate
     * @return
     */
    public Integer update(Donate Donate);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
