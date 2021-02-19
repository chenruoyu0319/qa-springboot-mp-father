package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.qa.annotation.*;
import com.cry.qa.constant.RedisReturnType;
import com.cry.qa.dao.MessageDao;
import com.cry.qa.domain.Message;
import com.cry.qa.domain.Question;
import com.cry.qa.domain.User;
import com.cry.qa.request.MsgListReq;
import com.cry.qa.request.SendMsgReq;
import com.cry.qa.response.PageObject;
import com.cry.qa.response.UserViewModel;
import com.cry.qa.service.LoginService;
import com.cry.qa.service.MessageService;
import com.cry.qa.utils.NormalException;
import com.cry.qa.utils.TimestampUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.cry.qa.utils.Const.DIRECT_MESSAGE;
import static com.cry.qa.utils.Const.SYSTEM_MESSAGE;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-18 22:46
 * @Modified By:
 */
@Transactional
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements IService<Message>, MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private LoginService loginService;

    /**
     * 发送站内私信
     *
     * @param req
     * @throws NormalException
     */

    @Override
    public void send(SendMsgReq req) throws NormalException {

        UserViewModel user = (UserViewModel) loginService.getUserViewModel();
        if (user == null) {
            throw new NormalException("亲！等个录先~~");
        }

        Message msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setMsgTo(req.getMsgTo());
        msg.setContent(req.getContent());
        msg.setInsertTime(TimestampUtil.getTimestampNow());
        msg.setType(DIRECT_MESSAGE);
        msg.setMsgFrom(user.getId());
        msg.setFromName(user.getName());
        msg.setTitle(user.getName() + "发出的新消息");
        save(msg);
    }

    /**
     * 通知问题被别人回复
     *
     * @param user
     * @param question
     */
    @Override
    public void notify(User user, Question question) {

        // 自己回复自己的问题,无需通知
        if (user.getId().equals(question.getUserId())) {
            return;
        }

        Message msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setMsgTo(question.getUserId());
        msg.setContent(user.getName() + "回复了" + question.getTitle());
        msg.setInsertTime(TimestampUtil.getTimestampNow());
        msg.setType(SYSTEM_MESSAGE);
        msg.setMsgFrom("system");
        msg.setFromName("系统");
        msg.setTitle("通知");
        msg.setHref("/questions/detail?id=" + question.getId());
        save(msg);
    }

    /**
     * 分页查询消息
     *
     * @param req
     * @return
     * @throws Exception
     */
    @Redis(returnType = RedisReturnType.LIST)
    //@RedisCachePageObject
    @Override
    public PageObject<Message> queryMsgList(MsgListReq req) throws Exception {

        UserViewModel user = (UserViewModel) loginService.getUserViewModel();
        if (user == null) {
            throw new Exception("亲！等个录先~~");
        }

        PageObject<Message> obj = new PageObject<>();
        IPage<Message> questionPage = page(new Page<Message>(req.getIndex(), req.getSize()),
                new QueryWrapper<Message>().eq("`msg_to`", user.getId()).or()
                        .eq("`msg_from`", user.getId()).orderByDesc("time"));

        obj.setSize(req.getSize());
        obj.setTotal((int) questionPage.getTotal());
        obj.setObjects(questionPage.getRecords());

        return obj;
    }

    /**
     * 删除消息
     *
     * @param id
     * @throws NormalException
     */
    @Override
    public void del(String id) throws NormalException {

        UserViewModel user = (UserViewModel) loginService.getUserViewModel();
        if (user == null) {
            throw new NormalException("亲！等个录先~~");
        }
        removeById(id);
    }

    /**
     * 删除某个用户的全部消息
     *
     * @throws NormalException
     */
    @Override
    public void delAll() throws NormalException {

        UserViewModel user = (UserViewModel) loginService.getUserViewModel();
        if (user == null) {
            throw new NormalException("亲！等个录先~~");
        }

        messageDao.deleteByUser(user.getId());
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
    public List<Message> findPage(Message condition, Integer pageNum, Integer pageSize) {
        PageInfo<Message> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            messageDao.selectList(Wrappers.<Message>query(condition));
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
    public List<Message> find(Message condition) {
        return messageDao.selectList(Wrappers.query(condition));
    }

    /**
     * 添加
     *
     * @param message
     * @return
     */
    @Override
    public Integer add(Message message) {
        return messageDao.insert(message);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public Message findById(String id) {
        return messageDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param message
     * @return
     */
    @Override
    public Integer update(Message message) {
        return messageDao.updateById(message);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return messageDao.deleteById(id);
    }
}
