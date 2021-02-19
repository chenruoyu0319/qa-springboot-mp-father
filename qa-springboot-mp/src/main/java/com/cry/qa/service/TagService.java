package com.cry.qa.service;

import com.cry.qa.domain.Tag;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface TagService {

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Tag> findPage(Tag condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Tag> find(Tag condition);

    /**
     * 添加
     *
     * @param Tag
     * @return
     */
    public Integer add(Tag Tag);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public Tag findById(String id);

    /**
     * 修改
     *
     * @param Tag
     * @return
     */
    public Integer update(Tag Tag);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
