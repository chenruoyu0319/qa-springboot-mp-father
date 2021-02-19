package com.cry.qa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cry.qa.domain.Message;
import org.springframework.stereotype.Repository;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-20 17:11
 * @Modified By:
 */
@Repository
public interface MessageDao extends BaseMapper<Message> {

    /**
     * 根据用户id删除全部消息
     * @param userid
     */
    void deleteByUser(String userid);
}
