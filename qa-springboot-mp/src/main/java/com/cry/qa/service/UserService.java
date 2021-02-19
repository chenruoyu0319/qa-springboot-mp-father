package com.cry.qa.service;

import com.cry.qa.domain.User;
import com.cry.qa.request.AddUserReq;
import com.cry.qa.request.LoginRequest;
import com.cry.qa.request.UpUserReq;
import com.cry.qa.response.PageObject;
import com.cry.qa.response.UserViewModel;
import com.cry.qa.utils.NormalException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface UserService {

    /**
     * 用户登录功能
     *
     * @param req
     * @return
     * @throws NormalException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public Object login(LoginRequest req) throws NormalException, UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 自定义的分页查询功能、
     * 显示给前端一个自定义的视图UserViewModel
     *
     * @return
     */
    public PageObject<UserViewModel> getNew();

    /**
     * 新用户注册功能
     *
     * @param req
     * @return
     * @throws NormalException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public String regNew(AddUserReq req) throws NormalException, UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * VIP升级功能
     *
     * @param req
     * @return
     * @throws NormalException
     */
    public Boolean up(UpUserReq req) throws NormalException;

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
