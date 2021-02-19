package com.cry.qa.controller;

import com.cry.qa.domain.Answer;
import com.cry.qa.http.HttpResult;
import com.cry.qa.service.AnswerService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-06 23:02
 * @Modified By:
 */

@Api(value = "Answer管理", protocols = "http", description = "Answer管理接口")
@RestController
@RequestMapping("/answer")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "Answer分页查询", notes = "接受查询条件answer和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "answer", value = "查询条件answer",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @PostMapping("/find/{page}/{size}")
    public HttpResult getAnswerListByPage(@PathVariable Integer page, @PathVariable("size") Integer size, @RequestBody Answer answer) {

        try {
            List<Answer> answerListByPage = answerService.findPage(answer, page, size);
            return HttpResult.ok(answerListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询Answer", notes = "接受查询条件answer")
    @ApiImplicitParam(paramType = "body", name = "answer", value = "查询条件answer",
            required = true, dataType = "Json")
    @PostMapping("/find")
    public HttpResult getAnswerList(@RequestBody Answer answer) {
        try {
            List<Answer> answerList = answerService.find(answer);
            return HttpResult.ok(answerList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询Answer", notes = "接受Answer-id")
    @ApiImplicitParam(paramType = "path", name = "answerId", value = "Answer-id", required =
            true, dataType = "String")
    @GetMapping("/find/{answerId}")
    public HttpResult getAnswerById(@PathVariable String answerId) {
        try {
            Answer answer = answerService.findById(answerId);
            return HttpResult.ok(answer);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "新增Answer", notes = "接受新增answer")
    @ApiImplicitParam(paramType = "body", name = "answer", value = "新增answer",
            required = true, dataType = "Json")
    @PostMapping("/add")
    public HttpResult addAnswer(@RequestBody Answer answer) {
        try {
            Integer addResult = answerService.add(answer);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "更新Answer", notes = "接受更新answer")
    @ApiImplicitParam(paramType = "body", name = "answer", value = "更新answer",
            required = true, dataType = "Json")
    @PutMapping("/update")
    public HttpResult updateAnswer(@RequestBody Answer answer) {
        try {
            Integer updateResult = answerService.update(answer);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除Answer", notes = "接受要删除的Answer-id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Answer-id",
            required = true, dataType = "String")
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleAnswer(@PathVariable String id) {
        try {
            Integer deleteResult = answerService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
