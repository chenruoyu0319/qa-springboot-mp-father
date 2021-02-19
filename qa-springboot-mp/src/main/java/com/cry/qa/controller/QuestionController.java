package com.cry.qa.controller;

import com.cry.qa.domain.Question;
import com.cry.qa.http.HttpResult;
import com.cry.qa.request.*;
import com.cry.qa.response.AnswerViewModel;
import com.cry.qa.response.PageObject;
import com.cry.qa.response.QuestionViewModel;
import com.cry.qa.service.LoginService;
import com.cry.qa.service.QuestionService;
import com.cry.qa.utils.NormalException;
import com.cry.qa.utils.ResultConstCode;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-06 23:02
 * @Modified By:
 */

@Api(value = "Question管理", protocols = "http", description = "Question管理接口")
@RestController
@RequestMapping("/question")
@CrossOrigin //CORS标准注解跨域
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取贴子列表
     *
     * @param questionListReq
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "获取贴子列表")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public HttpResult queryList(@RequestBody QuestionListReq questionListReq) {

        try {
            PageObject<QuestionViewModel> question = questionService.queryList(questionListReq);
            if (question == null) {
                return HttpResult.error("帖子不存在");
            }
            return HttpResult.ok(question, "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 近期热门贴子
     *
     * @throws IOException
     */
    @ApiOperation(httpMethod = "GET", value = "近期热门贴子")
    @RequestMapping(value = "/getHot", method = RequestMethod.GET)
    public HttpResult getHot() {

        try {
            PageObject<QuestionViewModel> hot = questionService.getHot();
            if (hot == null) {
                return HttpResult.error("帖子不存在");
            }
            return HttpResult.ok(hot, "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 近期热门贴子
     *
     * @throws IOException
     */
    @ApiOperation(httpMethod = "GET", value = "获取一个贴子的详细情况")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public HttpResult getOne(String id) {

        try {
            QuestionViewModel get = questionService.get(id);
            if (get == null) {
                return HttpResult.error("该帖已不存在");
            }
            return HttpResult.ok(get, "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 获取问题的答案
     *
     * @param req
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "获取问题的答案")
    @RequestMapping(value = "/queryAnswersOfQuestion", method = RequestMethod.POST)
    public HttpResult queryAnswersOfQuestion(@RequestBody QueryAnswersOfQuestionReq req) {

        try {
            PageObject<AnswerViewModel> answer = questionService.queryAnswersOfQuestion(req);
            if (answer == null) {
                return HttpResult.error("该问题暂时没有答案");
            }
            return HttpResult.ok(answer, "查询成功！");
        } catch (NormalException e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 发布一个贴子
     *
     * @param question
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "发布一个贴子")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpResult add(@RequestBody AddQuestionReq question) {

        try {
            String add = questionService.add(question);
            if (add == null) {
                return HttpResult.error("发布失败");
            }
            return HttpResult.ok(add, "发布成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 修改贴子
     *
     * @param question
     * @return
     */

    @ApiOperation(httpMethod = "POST", value = "修改贴子")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public HttpResult edit(@RequestBody AddQuestionReq question) {

        try {
            String edit = questionService.edit(question);
            if (edit == null) {
                return HttpResult.error("修改失败");
            }
            return HttpResult.ok(edit, "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 删除贴子
     *
     * @param id
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "删除贴子")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public HttpResult del(String id) {

        try {
            questionService.del(id);
            return HttpResult.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 管理员设置贴子的一些属性值, 比如置顶状态、精华帖
     *
     * @param id
     * @param field
     * @param rank
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "管理员设置贴子的一些属性值, 比如置顶状态、精华帖")
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public HttpResult set(String id,
                          @ApiParam(value = "当为stick标识【置顶】,为status标识【精贴】")
                                  String field,
                          @ApiParam(value = "1为设置，0为取消")
                                  int rank) {
        try {
            questionService.set(id, field, rank);
            return HttpResult.ok("设置成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 回答一个贴子
     *
     * @param addAnswerRequest
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "回答一个贴子")
    @RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
    public HttpResult addAnswer(@RequestBody AddAnswerRequest addAnswerRequest) {

        try {
            String s = questionService.addAnswer(addAnswerRequest);
            if (s == null) {
                return HttpResult.ok("回答失败！");
            }
            return HttpResult.ok(s, "回答成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 删除一个回答
     *
     * @param id
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "删除一个回复")
    @RequestMapping(value = "/delAnswer", method = RequestMethod.POST)
    public HttpResult delAnswer(String id) {

        try {
            questionService.delAnswer(id);
            return HttpResult.ok("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 采纳一个回复为最优回复（即问题的答案）
     *
     * @param id
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "采纳一个回复为最优回复（即问题的答案）")
    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public HttpResult accept(String id) {

        try {
            questionService.accept(id);
            return HttpResult.ok("采纳成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * 获取用户发的贴子
     *
     * @param request
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "获取用户发的贴子")
    @RequestMapping(value = "/getByUser", method = RequestMethod.POST)
    public HttpResult getByUser(@RequestBody QueryQuestionsByUser request) {

        try {
            PageObject<QuestionViewModel> get = questionService.getByUser(request);
            return HttpResult.ok(get, "获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    /**
     * @param request
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "获取用户【回复过】的贴子")
    @RequestMapping(value = "/getByUserAnswer", method = RequestMethod.POST)
    public HttpResult getByUserAnswer(@RequestBody QueryQuestionsByUser request) {

        try {
            PageObject<QuestionViewModel> userAnswer = questionService.getByUserAnswer(request);
            if (userAnswer == null) {
                return HttpResult.error("获取失败");
            }
            return HttpResult.ok(userAnswer, "获取成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(e.getMessage());
        }
    }

    @ApiOperationSupport(author = "Chen ruoyu")
    @ApiOperation(value = "Question分页查询", notes = "接受查询条件question和分页参数page,rows")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "第几页", required =
                    true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "一页显示多少条数据",
                    required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "body", name = "question", value = "查询条件question",
                    required = true, dataType = "Json")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "按照条件分页查询id成功"),
            @ApiResponse(code = 500, message = "按照条件分页查询id失败,后台服务出现异常"),
            @ApiResponse(code = 401, message = "此操作无权限访问"),
            @ApiResponse(code = 400, message = "请求参数错误"),
    })
    @PostMapping("/find/{page}/{size}")
    public HttpResult getQuestionListByPage(@PathVariable Integer page, @PathVariable("size") Integer
            size, @RequestBody Question question) {

        try {
            List<Question> questionListByPage = questionService.findPage(question, page, size);
            return HttpResult.ok(questionListByPage);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }

    }

    @ApiOperation(value = "查询Question", notes = "接受查询条件question")
    @ApiImplicitParam(paramType = "body", name = "question", value = "查询条件question",
            required = true, dataType = "Json")
    @PostMapping("/find")
    public HttpResult getQuestionList(@RequestBody Question question) {
        try {
            List<Question> questionList = questionService.find(question);
            return HttpResult.ok(questionList);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "根据id查询Question", notes = "接受Question-id")
    @ApiImplicitParam(paramType = "path", name = "questionId", value = "Question-id", required =
            true, dataType = "String")
    @GetMapping("/find/{questionId}")
    public HttpResult getQuestionById(@PathVariable String questionId) {
        try {
            Question question = questionService.findById(questionId);
            return HttpResult.ok(question);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

/*    @ApiOperation(value = "新增Question", notes = "接受新增question")
    @ApiImplicitParam(paramType = "body", name = "question", value = "新增question",
            required = true, dataType = "Json")
    @PostMapping("/add")
    public HttpResult addQuestion(@RequestBody Question question) {
        try {
            Integer addResult = questionService.add(question);
            return HttpResult.ok(addResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }*/

    @ApiOperation(value = "更新Question", notes = "接受更新question")
    @ApiImplicitParam(paramType = "body", name = "question", value = "更新question",
            required = true, dataType = "Json")
    @PutMapping("/update")
    public HttpResult updateQuestion(@RequestBody Question question) {
        try {
            Integer updateResult = questionService.update(question);
            return HttpResult.ok(updateResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

    @ApiOperation(value = "删除Question", notes = "接受要删除的Question-id")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Question-id",
            required = true, dataType = "String")
    @DeleteMapping("/delete/{id}")
    public HttpResult deleleQuestion(@PathVariable String id) {
        try {
            Integer deleteResult = questionService.delete(id);
            return HttpResult.ok(deleteResult);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error();
        }
    }

}
