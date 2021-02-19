package com.cry.qa.controller;

import com.cry.qa.domain.SysConf;
import com.cry.qa.http.HttpResult;
import com.cry.qa.service.SysConfService;
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

@Api(value = "系统配置管理", protocols = "http", description = "系统配置管理接口")
@RestController
@RequestMapping("/sysConf")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class SysConfController {

    @Autowired
    private SysConfService sysConfService;

    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "系统配置分页查询", notes = "接受查询条件sysConf和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "sysConf", value = "查询条件sysConf",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @PostMapping("/find/{page}/{size}")
    public HttpResult getSysConfListByPage(@PathVariable Integer page, @PathVariable("size") Integer size, @RequestBody SysConf sysConf) {

        try {
            List<SysConf> sysConfListByPage = sysConfService.findPage(sysConf, page, size);
            return HttpResult.ok(sysConfListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询系统配置", notes = "接受查询条件sysConf")
    @ApiImplicitParam(paramType = "body", name = "sysConf", value = "查询条件sysConf",
            required = true, dataType = "Json")
    @PostMapping("/find")
    public HttpResult getSysConfList(@RequestBody SysConf sysConf) {
        try {
            List<SysConf> sysConfList = sysConfService.find(sysConf);
            return HttpResult.ok(sysConfList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询系统配置", notes = "接受系统配置id")
    @ApiImplicitParam(paramType = "path", name = "sysConfId", value = "系统配置id", required =
            true, dataType = "String")
    @GetMapping("/find/{sysConfId}")
    public HttpResult getSysConfById(@PathVariable String sysConfId) {
        try {
            SysConf sysConf = sysConfService.findById(sysConfId);
            return HttpResult.ok(sysConf);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "新增系统配置", notes = "接受新增sysConf")
    @ApiImplicitParam(paramType = "body", name = "sysConf", value = "新增sysConf",
            required = true, dataType = "Json")
    @PostMapping("/add")
    public HttpResult addSysConf(@RequestBody SysConf sysConf) {
        try {
            Integer addResult = sysConfService.add(sysConf);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "更新系统配置", notes = "接受更新sysConf")
    @ApiImplicitParam(paramType = "body", name = "sysConf", value = "更新sysConf",
            required = true, dataType = "Json")
    @PutMapping("/update")
    public HttpResult updateSysConf(@RequestBody SysConf sysConf) {
        try {
            Integer updateResult = sysConfService.update(sysConf);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除系统配置", notes = "接受要删除的系统配置id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "系统配置id",
            required = true, dataType = "String")
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleSysConf(@PathVariable String id) {
        try {
            Integer deleteResult = sysConfService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
