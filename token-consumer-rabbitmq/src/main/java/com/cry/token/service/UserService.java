package com.cry.token.service;

import com.cry.token.domain.User;
import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface UserService {

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<User> findPage(User condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<User> find(User condition);

    /**
     * 添加
     *
     * @param user
     * @return
     */
    public Integer add(User user);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public User findById(String id);

    /**
     * 修改
     *
     * @param user
     * @return
     */
    public Integer update(User user);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
