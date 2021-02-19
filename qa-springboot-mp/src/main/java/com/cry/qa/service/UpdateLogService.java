package com.cry.qa.service;

import com.cry.qa.domain.UpdateLog;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface UpdateLogService {

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<UpdateLog> findPage(UpdateLog condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<UpdateLog> find(UpdateLog condition);

    /**
     * 添加
     *
     * @param UpdateLog
     * @return
     */
    public Integer add(UpdateLog UpdateLog);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public UpdateLog findById(String id);

    /**
     * 修改
     *
     * @param UpdateLog
     * @return
     */
    public Integer update(UpdateLog UpdateLog);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
