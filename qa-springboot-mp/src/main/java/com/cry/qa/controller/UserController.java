package com.cry.qa.controller;

import com.alibaba.fastjson.JSON;
import com.cry.qa.domain.Token;
import com.cry.qa.domain.User;
import com.cry.qa.http.HttpResult;
import com.cry.qa.msgqueue.rabbitmq.config.RabbitMqConfig;
import com.cry.qa.request.AddUserReq;
import com.cry.qa.request.LoginRequest;
import com.cry.qa.request.UpUserReq;
import com.cry.qa.response.PageObject;
import com.cry.qa.response.UserViewModel;
import com.cry.qa.service.LoginService;
import com.cry.qa.service.TokenService;
import com.cry.qa.service.UserService;
import com.cry.qa.utils.Const;
import com.cry.qa.utils.NormalException;
import com.cry.qa.utils.UUIDStaticFactory;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-06 23:02
 * @Modified By:
 */

@Api(value = "用户管理", protocols = "http", description = "用户管理接口")
@Controller
@RequestMapping("/user")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier(value = "threadPoolExecutor")
    private Executor threadPoolExecutor;

    // Redis缓存解耦
/*    @Autowired
    CacheService cacheService;*/

    // 获取当前类的日志
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(httpMethod = "GET", value = "登出后跳转页面")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse out) {

        // 清空了cookie信息
        Cookie cookie = new Cookie(Const.COOKIE_LOGIN_USER, "");
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        out.addCookie(cookie);
        return "logout";
    }

    @ResponseBody
    @RequestMapping(value = "/getLoginUser", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "从Redis缓存获取已经登录的用户")
    public HttpResult getLoginUser() {
        try {
            UserViewModel userViewModel = (UserViewModel) loginService.getUserViewModel();
            if (userViewModel == null) {
                return HttpResult.error("用户未登录,请先登录！");
            }
            return HttpResult.ok(userViewModel, "用户已经登录！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "获取用户的基本信息")
    public HttpResult getById(String uid) {
        try {
            User user = userService.findById(uid);
            if (user == null) {
                HttpResult.error("用户不存在");
            }
            return HttpResult.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "新用户注册（添加用户）")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public HttpResult addUser(@RequestBody AddUserReq addUserReq) {
        try {
            // 返回新增用户id
            String id = userService.regNew(addUserReq);
            return HttpResult.ok(id, "注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpResult login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = (String) userService.login(loginRequest);
            // 用作将token持久化到Mysql备份
            String account = loginRequest.getAccount();
            Map<String, Object> map = new HashMap<>();
            map.put("token",token);
            map.put("account",account);
            // 从线程池中取一个线程,向中间件异步IO
            threadPoolExecutor.execute(() -> {
                try {
                    // 发送message到消息中间件交换机: TOKEN_TOPIC_EXCHANGE
                    rabbitTemplate.convertAndSend(RabbitMqConfig.TOKEN_TOPIC_EXCHANGE, "token.insert", map);
                    logger.info(Thread.currentThread().getName() + "======Token持久化任务执行完成======");
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            });

            return HttpResult.ok(token, "登录成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "获取最新注册的用户")
    @RequestMapping(value = "/getNew", method = RequestMethod.GET)
    public HttpResult getNew() {
        try {
            PageObject<UserViewModel> aNew = userService.getNew();
            return HttpResult.ok(aNew);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "升级用户")
    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public HttpResult up(@RequestBody UpUserReq req) {
        try {
            Boolean up = userService.up(req);
            return HttpResult.ok(up);
        } catch (NormalException e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "用户分页查询", notes = "接受查询条件user和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "user", value = "查询条件user",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @ResponseBody
    @PostMapping("/find/{page}/{size}")
    public HttpResult getUserListByPage(@PathVariable Integer page, @PathVariable("size") Integer size, @RequestBody User user) {

        try {
            List<User> userListByPage = userService.findPage(user, page, size);
            return HttpResult.ok(userListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询用户", notes = "接受查询条件user")
    @ApiImplicitParam(paramType = "body", name = "user", value = "查询条件user",
            required = true, dataType = "Json")
    @ResponseBody
    @PostMapping("/find")
    public HttpResult getUserList(@RequestBody User user) {
        try {
            List<User> userList = userService.find(user);
            return HttpResult.ok(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询用户", notes = "接受用户id")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required =
            true, dataType = "String")
    @ResponseBody
    @GetMapping("/find/{userId}")
    public HttpResult getUserById(@PathVariable String userId) {
        try {
            User user = userService.findById(userId);
            return HttpResult.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "新增用户", notes = "接受新增user")
    @ApiImplicitParam(paramType = "body", name = "user", value = "新增user",
            required = true, dataType = "Json")
    @ResponseBody
    @PostMapping("/add")
    public HttpResult addUser(@RequestBody User user) {
        try {
            Integer addResult = userService.add(user);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "更新用户", notes = "接受更新user")
    @ApiImplicitParam(paramType = "body", name = "user", value = "更新user",
            required = true, dataType = "Json")
    @ResponseBody
    @PutMapping("/update")
    public HttpResult updateUser(@RequestBody User user) {
        try {
            Integer updateResult = userService.update(user);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除用户", notes = "接受要删除的用户id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id",
            required = true, dataType = "String")
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleUser(@PathVariable String id) {
        try {
            Integer deleteResult = userService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
