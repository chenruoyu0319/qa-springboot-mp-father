package com.cry.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.qa.annotation.RedisCache;
import com.cry.qa.annotation.RedisCacheList;
import com.cry.qa.annotation.RedisCachePage;
import com.cry.qa.annotation.SetTokenRedisCache;
import com.cry.qa.domain.UserExample;
import com.cry.qa.request.AddUserReq;
import com.cry.qa.request.LoginRequest;
import com.cry.qa.request.UpUserReq;
import com.cry.qa.response.PageObject;
import com.cry.qa.response.UserViewModel;
import com.cry.qa.service.LoginService;
import com.cry.qa.utils.MD5;
import com.cry.qa.utils.NormalException;
import com.cry.qa.utils.TimestampUtil;
import com.github.pagehelper.PageHelper;
import com.cry.qa.dao.UserDao;
import com.cry.qa.domain.User;
import com.cry.qa.service.UserService;
import com.github.pagehelper.PageInfo;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-18 22:46
 * @Modified By:
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IService<User>, UserService {

    @Autowired
    private UserDao userDao;

    // 用注解解耦Redis缓存
/*    @Autowired
    private CacheService cacheService;*/

    @Autowired
    private LoginService loginService;

    // 获取当前类的日志
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 创建一个Bean转换工具: DozerBeanMapper
     */
    /*DozerBeanMapper mapper = new DozerBeanMapper();*/

    /**
     * 用户登录功能
     *
     * @param req
     * @return
     * @throws NormalException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @SetTokenRedisCache
    @Override
    public Object login(LoginRequest req) throws NormalException, UnsupportedEncodingException, NoSuchAlgorithmException {

        // 根据"account"条件查询
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("account", req.getAccount());

        // 调用IService<User>封装的getOne方法,根据wrapper条件构造器查询
        User userInfo = getOne(wrapper);
        if (userInfo == null) {
            // 500, 登录用户不存在
            throw new NormalException("登录用户不存在");
        }

        // 数据库中MD5加密后的pwd匹配LoginRequst传来的MD5-pwd,不一致则抛出登录密码错误
        if (!userInfo.getPwd().equals(MD5.Encode(req.getPwd()))) {
            throw new NormalException("登录密码错误");
        }

        // LoginRequst中的用户信息与数据库中的信息匹配上后,确认用户身份
        // 生成token令牌并返回 => 抽象到redis中
        return userInfo;
    }

    /**
     * 获取最新注册的用户,分页查询,显示给前端一个自定义的视图UserViewModel
     *
     * @return
     */
    @Override
    public PageObject<UserViewModel> getNew() {

        // 索引
        int index = 1;
        // 每页行数
        int size = 12;

        // 查询条件
        UserExample exp = new UserExample();

        // 根据createTime降序排列（最新的在上面）
        exp.setOrderByClause("createTime desc");

        // 创建一个分页查询结果对象
        PageObject<UserViewModel> obj = new PageObject<UserViewModel>();
        obj.setSize(size);
        obj.setTotal(userDao.countByExample(exp));

        // 计算每页开始的startIndex,例如第2页的第1条
        int startIndex = (index - 1) * size;
        // 设置查询的开始索引
        exp.setOffset(startIndex);
        // 设置查询的每页行数
        exp.setLimit(size);
        // 查询出分页结果
        List<User> users = userDao.selectByExample(exp);
        if (users != null && users.size() > 0) {
            // 如果分页结果有值, DO -> VO: List<User> -> List<UserViewModel>
            obj.setObjects(UserViewModel.userListFrom(users));
        }

        return obj;
    }

    /**
     * 注册新用户
     *
     * @param req
     * @return
     * @throws NormalException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public String regNew(AddUserReq req) throws NormalException, UnsupportedEncodingException, NoSuchAlgorithmException {

        // 非法的账户/昵称集合, 不开放注册
        String[] invalidAccounts = new String[]{"admin", "administrator", "管理员", "超级管理员"};
        // 创建Arrays的内部类String, 判断注册请求AddUserReq内的属性是否非法
        if (Arrays.asList(invalidAccounts).contains(req.getAccount()) || Arrays.asList(invalidAccounts).contains(req.getName())) {
            throw new NormalException("非法账号或用户名字");
        }

        // 判断账号是否为空
        if (Strings.isNullOrEmpty(req.getAccount())) {
            throw new NormalException("账号不能为空");
        }

        // 用IService的count方法计数根据account查询的结果！>0, 来判断账号是否已被注册
        if (count(new QueryWrapper<User>().eq("account", req.getAccount())) > 0) {
            throw new NormalException("账号已存在");
        }

        // 账号未存在,则注册
        // 未加密: AddUserReq(String name, String account, String pwd, String checkPass)
        User user = UserViewModel.userFrom(req);
        // 对密码加密
        user.setPwd(MD5.Encode(req.getPwd()));
        // 生成一个32位带"-"的UUID, 作为userId
        user.setId(UUID.randomUUID().toString());
        user.setPic(new Random().nextInt(9) + ".jpg");
        user.setCreateTime(TimestampUtil.getTimestampNow());
        // 保存加密 + 补充属性后的User
        save(user);
        // 返回新注册的userId
        return user.getId();
    }

    /**
     * VIP升级
     *
     * @param req
     * @throws NormalException
     */
    @Override
    public Boolean up(UpUserReq req) throws NormalException {

        // VIP升级前先登录
        // 从缓存中根据Token把登录态用户查出来
        UserViewModel userViewModel = (UserViewModel)loginService.getUserViewModel();

        //未登录
        if (userViewModel == null) {
            throw new NormalException("亲！等个录先~~");
        }

        //权限不足
        if (userViewModel.getAuth() != 1) {
            throw new NormalException("只有管理员才能进行该操作");
        }

        // IService中主键查询方法
        User user = getById(req.getUid());
        // 用户未注册
        if (user == null) {
            throw new NormalException("该用户id不存在");
        }

        // 查到登录态用户, 升级会员级别
        user.setRmb(req.getRmb());
        // 调用ServiceImpl更新方法
        boolean b = saveOrUpdate(user);
        if (b) {
            logger.info("您的VIP已升级为: " + user.getRmb());
        }
        logger.error("VIP升级失败, 服务器维护中");
        return b;
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
    public List<User> findPage(User condition, Integer pageNum, Integer pageSize) {
        PageInfo<User> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            userDao.selectList(Wrappers.query(condition));
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
    public List<User> find(User condition) {
        return userDao.selectList(Wrappers.query(condition));

    }

    /**
     * 添加
     *
     * @param user
     * @return
     */
    @Override
    public Integer add(User user) {
        return userDao.insert(user);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public User findById(String id) {
        return userDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @Override
    public Integer update(User user) {
        return userDao.updateById(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return userDao.deleteById(id);
    }
}
