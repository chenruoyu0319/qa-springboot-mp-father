package com.cry.qa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cry.qa.domain.User;
import com.cry.qa.domain.UserExample;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: haohaoxuexiyai
 * @Description:
 * @Date Created in:  2021-01-20 17:11
 * @Modified By:
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 复杂查询: 根据查询条件UserExample计数
     *
     * @param example
     * @return
     */
    Integer countByExample(UserExample example);

    /**
     * 复杂查询: 根据查询条件UserExample查询结果集List<User>
     *
     * @param example
     * @return
     */
    List<User> selectByExample(UserExample example);
}
