package com.cry.qa.service;

import com.cry.qa.domain.Question;
import com.cry.qa.request.*;
import com.cry.qa.response.AnswerViewModel;
import com.cry.qa.response.PageObject;
import com.cry.qa.response.QuestionViewModel;
import com.cry.qa.utils.NormalException;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-19 17:43
 * @Modified By:
 */
public interface QuestionService {

    /**
     * 模糊查询/排序查询/动态查询,获取分页Question查询结果
     *
     * @param req
     * @return
     */
    PageObject<QuestionViewModel> queryList(QuestionListReq req);

    /**
     * 发布一个贴子: 新增Question
     *
     * @param req
     * @return
     * @throws Exception
     */
    String add(AddQuestionReq req) throws Exception;

    /**
     * 修改贴子: 修改Question
     *
     * @param req
     * @return
     * @throws Exception
     */
    String edit(AddQuestionReq req) throws Exception;

    /**
     * 删除帖子: 删除Question
     *
     * @param id
     * @throws Exception
     */
    void del(String id) throws Exception;

    /**
     * 管理员设置贴子的一些属性值, 比如置顶状态、精华帖
     *
     * @param id
     * @param field
     * @param rank
     * @throws Exception
     */
    void set(String id, String field, int rank) throws Exception;

    /**
     * 删除回答
     *
     * @param id
     * @throws Exception
     */
    void delAnswer(String id) throws Exception;

    /**
     * 采纳回答
     *
     * @param id
     * @throws Exception
     */
    void accept(String id) throws Exception;

    /**
     * 获取用户发的贴子
     *
     * @param request
     * @return
     */
    PageObject<QuestionViewModel> getByUser(QueryQuestionsByUser request);

    /**
     * 获取用户回答过的问题列表
     *
     * @param req
     * @return
     */
    PageObject<QuestionViewModel> getByUserAnswer(QueryQuestionsByUser req);

    /**
     * 获取某个具体问题的回答
     *
     * @param req
     * @return
     * @throws NormalException
     */
    PageObject<AnswerViewModel> queryAnswersOfQuestion(QueryAnswersOfQuestionReq req) throws NormalException;

    /**
     * 获取热门帖子
     *
     * @return
     */
    PageObject<QuestionViewModel> getHot();

    /**
     * 新增回答
     *
     * @param request
     * @return
     * @throws Exception
     */
    String addAnswer(AddAnswerRequest request) throws Exception;

    /**
     * get
     *
     * @param id
     * @return
     */
    QuestionViewModel get(String id);

    /**
     * 分页查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Question> findPage(Question condition, Integer pageNum, Integer pageSize);

    /**
     * 查询
     *
     * @param condition 查询条件
     * @return
     */
    public List<Question> find(Question condition);

    /**
     * 添加
     *
     * @param Question
     * @return
     */
/*    public Integer add(Question Question);*/

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    public Question findById(String id);

    /**
     * 修改
     *
     * @param Question
     * @return
     */
    public Integer update(Question Question);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Integer delete(String id);
}
