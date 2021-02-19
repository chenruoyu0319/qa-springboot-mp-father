package com.cry.qa.controller;

import com.cry.qa.domain.Donate;
import com.cry.qa.http.HttpResult;
import com.cry.qa.service.DonateService;
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

@Api(value = "打赏管理", protocols = "http", description = "打赏管理接口")
@RestController
@RequestMapping("/donate")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class DonateController {

    @Autowired
    private DonateService donateService;

    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "打赏分页查询", notes = "接受查询条件donate和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "donate", value = "查询条件donate",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @PostMapping("/find/{page}/{size}")
    public HttpResult getDonateListByPage(@PathVariable Integer page, @PathVariable("size") Integer size, @RequestBody Donate donate) {

        try {
            List<Donate> donateListByPage = donateService.findPage(donate, page, size);
            return HttpResult.ok(donateListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询打赏", notes = "接受查询条件donate")
    @ApiImplicitParam(paramType = "body", name = "donate", value = "查询条件donate",
            required = true, dataType = "Json")
    @PostMapping("/find")
    public HttpResult getDonateList(@RequestBody Donate donate) {
        try {
            List<Donate> donateList = donateService.find(donate);
            return HttpResult.ok(donateList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询打赏", notes = "接受打赏id")
    @ApiImplicitParam(paramType = "path", name = "donateId", value = "打赏id", required =
            true, dataType = "String")
    @GetMapping("/find/{donateId}")
    public HttpResult getDonateById(@PathVariable String donateId) {
        try {
            Donate donate = donateService.findById(donateId);
            return HttpResult.ok(donate);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "新增打赏", notes = "接受新增donate")
    @ApiImplicitParam(paramType = "body", name = "donate", value = "新增donate",
            required = true, dataType = "Json")
    @PostMapping("/add")
    public HttpResult addDonate(@RequestBody Donate donate) {
        try {
            Integer addResult = donateService.add(donate);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "更新打赏", notes = "接受更新donate")
    @ApiImplicitParam(paramType = "body", name = "donate", value = "更新donate",
            required = true, dataType = "Json")
    @PutMapping("/update")
    public HttpResult updateDonate(@RequestBody Donate donate) {
        try {
            Integer updateResult = donateService.update(donate);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除打赏", notes = "接受要删除的打赏id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "打赏id",
            required = true, dataType = "String")
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleDonate(@PathVariable String id) {
        try {
            Integer deleteResult = donateService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
