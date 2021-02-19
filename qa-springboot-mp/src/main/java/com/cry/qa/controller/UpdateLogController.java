package com.cry.qa.controller;

import com.cry.qa.domain.UpdateLog;
import com.cry.qa.http.HttpResult;
import com.cry.qa.service.UpdateLogService;
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

@Api(value = "更新日志管理", protocols = "http", description = "更新日志管理接口")
@RestController
@RequestMapping("/updateLog")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class UpdateLogController {

    @Autowired
    private UpdateLogService updateLogService;

    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "更新日志分页查询", notes = "接受查询条件updateLog和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "updateLog", value = "查询条件updateLog",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @PostMapping("/find/{page}/{size}")
    public HttpResult getUpdateLogListByPage(@PathVariable Integer page, @PathVariable("size") Integer size, @RequestBody UpdateLog updateLog) {

        try {
            List<UpdateLog> updateLogListByPage = updateLogService.findPage(updateLog, page, size);
            return HttpResult.ok(updateLogListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询更新日志", notes = "接受查询条件updateLog")
    @ApiImplicitParam(paramType = "body", name = "updateLog", value = "查询条件updateLog",
            required = true, dataType = "Json")
    @PostMapping("/find")
    public HttpResult getUpdateLogList(@RequestBody UpdateLog updateLog) {
        try {
            List<UpdateLog> updateLogList = updateLogService.find(updateLog);
            return HttpResult.ok(updateLogList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询更新日志", notes = "接受更新日志id")
    @ApiImplicitParam(paramType = "path", name = "updateLogId", value = "更新日志id", required =
            true, dataType = "String")
    @GetMapping("/find/{updateLogId}")
    public HttpResult getUpdateLogById(@PathVariable String updateLogId) {
        try {
            UpdateLog updateLog = updateLogService.findById(updateLogId);
            return HttpResult.ok(updateLog);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "新增更新日志", notes = "接受新增updateLog")
    @ApiImplicitParam(paramType = "body", name = "updateLog", value = "新增updateLog",
            required = true, dataType = "Json")
    @PostMapping("/add")
    public HttpResult addUpdateLog(@RequestBody UpdateLog updateLog) {
        try {
            Integer addResult = updateLogService.add(updateLog);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "更新更新日志", notes = "接受更新updateLog")
    @ApiImplicitParam(paramType = "body", name = "updateLog", value = "更新updateLog",
            required = true, dataType = "Json")
    @PutMapping("/update")
    public HttpResult updateUpdateLog(@RequestBody UpdateLog updateLog) {
        try {
            Integer updateResult = updateLogService.update(updateLog);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除更新日志", notes = "接受要删除的更新日志id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "更新日志id",
            required = true, dataType = "String")
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleUpdateLog(@PathVariable String id) {
        try {
            Integer deleteResult = updateLogService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
