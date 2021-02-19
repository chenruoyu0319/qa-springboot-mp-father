package com.cry.qa.service;

import com.cry.qa.domain.Message;
import com.cry.qa.domain.Question;
import com.cry.qa.domain.User;
import com.cry.qa.request.MsgListReq;
import com.cry.qa.request.SendMsgReq;
import com.cry.qa.response.PageObject;
import com.cry.qa.utils.NormalException;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface MessageService {

    /**
     * 发送站内私信
     *
     * @param req
     * @throws NormalException
     */
    public void send(SendMsgReq req) throws NormalException;

    /**
     * 通知问题被别人回复
     *
     * @param user
     * @param question
     */
    public void notify(User user, Question question);

    /**
     * 分页查询消息
     *
     * @param req
     * @return
     * @throws Exception
     */
    public PageObject<Message> queryMsgList(MsgListReq req) throws Exception;

    /**
     * 删除消息
     *
     * @param id
     * @throws NormalException
     */
    public void del(String id) throws NormalException;

    /**
     * 删除某个用户的全部消息
     *
     * @throws NormalException
     */
    public void delAll() throws NormalException;


    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Message> findPage(Message condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Message> find(Message condition);

    /**
     * 添加
     *
     * @param Message
     * @return
     */
    public Integer add(Message Message);

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public Message findById(String id);

    /**
     * 修改
     *
     * @param Message
     * @return
     */
    public Integer update(Message Message);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
