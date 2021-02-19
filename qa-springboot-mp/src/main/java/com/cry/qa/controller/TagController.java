package com.cry.qa.controller;

import com.cry.qa.domain.Tag;
import com.cry.qa.http.HttpResult;
import com.cry.qa.service.TagService;
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

@Api(value = "标签管理", protocols = "http", description = "标签管理接口")
@RestController
@RequestMapping("/tag")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "标签分页查询", notes = "接受查询条件tag和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "tag", value = "查询条件tag",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @PostMapping("/find/{page}/{size}")
    public HttpResult getTagListByPage(@PathVariable Integer page, @PathVariable("size") Integer size, @RequestBody Tag tag) {

        try {
            List<Tag> tagListByPage = tagService.findPage(tag, page, size);
            return HttpResult.ok(tagListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询标签", notes = "接受查询条件tag")
    @ApiImplicitParam(paramType = "body", name = "tag", value = "查询条件tag",
            required = true, dataType = "Json")
    @PostMapping("/find")
    public HttpResult getTagList(@RequestBody Tag tag) {
        try {
            List<Tag> tagList = tagService.find(tag);
            return HttpResult.ok(tagList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询标签", notes = "接受标签id")
    @ApiImplicitParam(paramType = "path", name = "tagId", value = "标签id", required =
            true, dataType = "String")
    @GetMapping("/find/{tagId}")
    public HttpResult getTagById(@PathVariable String tagId) {
        try {
            Tag tag = tagService.findById(tagId);
            return HttpResult.ok(tag);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "新增标签", notes = "接受新增tag")
    @ApiImplicitParam(paramType = "body", name = "tag", value = "新增tag",
            required = true, dataType = "Json")
    @PostMapping("/add")
    public HttpResult addTag(@RequestBody Tag tag) {
        try {
            Integer addResult = tagService.add(tag);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "更新标签", notes = "接受更新tag")
    @ApiImplicitParam(paramType = "body", name = "tag", value = "更新tag",
            required = true, dataType = "Json")
    @PutMapping("/update")
    public HttpResult updateTag(@RequestBody Tag tag) {
        try {
            Integer updateResult = tagService.update(tag);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除标签", notes = "接受要删除的标签id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "标签id",
            required = true, dataType = "String")
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleTag(@PathVariable String id) {
        try {
            Integer deleteResult = tagService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
