package com.cry.qa.controller;

import com.cry.qa.domain.Message;
import com.cry.qa.http.HttpResult;
import com.cry.qa.request.DelMsgReq;
import com.cry.qa.request.MsgListReq;
import com.cry.qa.request.SendMsgReq;
import com.cry.qa.response.PageObject;
import com.cry.qa.service.MessageService;
import com.cry.qa.utils.NormalException;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-06 23:02
 * @Modified By:
 */

@Api(value = "消息管理", protocols = "http", description = "消息管理接口")
@RestController
@RequestMapping("/message")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation(httpMethod = "POST", value = "分页查询消息")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public HttpResult get(@RequestBody MsgListReq msgListReq) {
        try {
            // 分页查询msg
            PageObject<Message> msg = messageService.queryMsgList(msgListReq);
            return HttpResult.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ApiOperation(httpMethod = "POST", value = "发送站内私信")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public HttpResult send(@RequestBody SendMsgReq req) {

        try {
            // 发送站内私信
            messageService.send(req);
            return HttpResult.ok("私信发送成功！");
        } catch (NormalException e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ApiOperation(httpMethod = "POST", value = "删除消息")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public HttpResult del(@RequestBody DelMsgReq req) {

        try {
            messageService.del(req.getId());
            return HttpResult.ok("删除成功！");

        } catch (NormalException e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ApiOperation(httpMethod = "GET", value = "删除")
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public HttpResult clear() {

        try {
            messageService.delAll();
            return HttpResult.ok("全部删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }


    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "信息分页查询", notes = "接受查询条件message和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "message", value = "查询条件message",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @PostMapping("/find/{page}/{size}")
    public HttpResult getMessageListByPage(@PathVariable Integer page, @PathVariable("size") Integer size, @RequestBody Message message) {

        try {
            List<Message> messageListByPage = messageService.findPage(message, page, size);
            return HttpResult.ok(messageListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询信息", notes = "接受查询条件message")
    @ApiImplicitParam(paramType = "body", name = "message", value = "查询条件message",
            required = true, dataType = "Json")
    @PostMapping("/find")
    public HttpResult getMessageList(@RequestBody Message message) {
        try {
            List<Message> messageList = messageService.find(message);
            return HttpResult.ok(messageList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询信息", notes = "接受信息id")
    @ApiImplicitParam(paramType = "path", name = "messageId", value = "信息id", required =
            true, dataType = "String")
    @GetMapping("/find/{messageId}")
    public HttpResult getMessageById(@PathVariable String messageId) {
        try {
            Message message = messageService.findById(messageId);
            return HttpResult.ok(message);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "新增信息", notes = "接受新增message")
    @ApiImplicitParam(paramType = "body", name = "message", value = "新增message",
            required = true, dataType = "Json")
    @PostMapping("/add")
    public HttpResult addMessage(@RequestBody Message message) {
        try {
            Integer addResult = messageService.add(message);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "更新信息", notes = "接受更新message")
    @ApiImplicitParam(paramType = "body", name = "message", value = "更新message",
            required = true, dataType = "Json")
    @PutMapping("/update")
    public HttpResult updateMessage(@RequestBody Message message) {
        try {
            Integer updateResult = messageService.update(message);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除信息", notes = "接受要删除的信息id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "信息id",
            required = true, dataType = "String")
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleMessage(@PathVariable String id) {
        try {
            Integer deleteResult = messageService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
