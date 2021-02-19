package com.cry.qa.service;

import com.cry.qa.domain.Answer;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */

public interface AnswerService {

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */

    public List<Answer> findPage(Answer condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Answer> find(Answer condition);

    /**
     * 添加
     *
     * @param user
     * @return
     */
    public Integer add(Answer user);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public Answer findById(String id);

    /**
     * 修改
     *
     * @param user
     * @return
     */
    public Integer update(Answer user);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
