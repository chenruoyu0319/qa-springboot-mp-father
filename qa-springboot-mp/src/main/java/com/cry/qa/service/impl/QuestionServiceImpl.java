package com.cry.qa.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.qa.annotation.*;
import com.cry.qa.dao.AnswerDao;
import com.cry.qa.dao.QuestionDao;
import com.cry.qa.dao.UserDao;
import com.cry.qa.domain.Answer;
import com.cry.qa.domain.Question;
import com.cry.qa.domain.QuestionExample;
import com.cry.qa.domain.User;
import com.cry.qa.request.*;
import com.cry.qa.response.AnswerViewModel;
import com.cry.qa.response.PageObject;
import com.cry.qa.response.QuestionViewModel;
import com.cry.qa.response.UserViewModel;
import com.cry.qa.service.LoginService;
import com.cry.qa.service.MessageService;
import com.cry.qa.service.QuestionService;
import com.cry.qa.utils.NormalException;
import com.cry.qa.utils.TimestampUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.assertj.core.util.Strings;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.cry.qa.utils.Const.STATUS;
import static com.cry.qa.utils.Const.STICK;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-18 22:46
 * @Modified By:
 */
@Transactional
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> implements IService<Question>, QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageService msgService;

    DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * 工具方法: 新增回答
     *
     * @param jid
     * @param content
     * @param user
     * @return
     */
    private String addAnswer(String jid, String content, UserViewModel user) {
        String id = UUID.randomUUID().toString();
        Answer answer = new Answer();
        answer.setId(id);
        answer.setUserId(user.getId());
        answer.setContent(content);
        answer.setAnswerTo(jid);
        answer.setInsertTime(TimestampUtil.getTimestampNow());
        answerDao.insert(answer);
        return id;
    }

    /**
     * 工具方法: 检查是否登录
     *
     * @return
     * @throws Exception
     */
    private UserViewModel checkLogin() throws Exception {
        UserViewModel user = (UserViewModel) loginService.getUserViewModel();
        if (user == null) {
            throw new Exception("请先登录");
        }
        return user;
    }

    /**
     * 工具方法: 新增浏览量
     *
     * @param id
     */
    private void addHitCnt(String id) {
        QuestionExample exp = new QuestionExample();
        QuestionExample.Criteria criteria = exp.createCriteria();
        criteria.andIdEqualTo(id);

        Question question = getById(id);
        question.setHits(question.getHits() + 1);
        questionDao.updateByExampleSelective(question, exp);
    }


    /**
     * 模糊查询/排序查询/动态查询,获取分页Question查询结果
     *
     * @param req
     * @return
     */
    @RedisCachePageObject
    @Override
    public PageObject<QuestionViewModel> queryList(QuestionListReq req) {

        int index = req.getIndex();
        int size = req.getSize();
        String type = req.getType();
        String key = req.getKey();

        // 默认页码: 1
        if (index == 0) {
            index = 1;
        }
        // 默认每页行数: 10
        if (size == 0) {
            size = 10;
        }
        // 默认Question类型: 空
        if (type == null) {
            type = "";
        }

        QuestionExample exp = new QuestionExample();
        // 设置排序规则: 置顶降序、时间降序
        exp.setOrderByClause("stick desc,time desc");

        // 设置复杂查询条件
        QuestionExample.Criteria criteria = exp.createCriteria();

        // 模糊查询,根据key匹配title
        if (key != null && !key.equals("")) {
            criteria.andTitleLike(key);
        }

        // Question类型: 已解决
        if (type.equals("resolved")) {
            criteria.andAcceptIsNotNull();
            // Question类型: 未解决
        } else if (type.equals("unresolved")) {
            criteria.andAcceptIsNull();
            // Question类型: 精华帖
        } else if (type.equals("wonderful")) {
            criteria.andStatusGreaterThan(0);
        }

        // 新增分页查询参数
        PageObject<QuestionViewModel> obj = new PageObject<>();
        obj.setSize(size);
        obj.setTotal(questionDao.countByExample(exp));

        // 每页第一条索引
        int startIndex = (index - 1) * size;
        // 复杂查询条件: offSet
        exp.setOffset(startIndex);
        // 复杂查询条件: limit
        exp.setLimit(size);
        obj.setObjects(questionDao.getQuestionVMs(exp));
        // 手动添加分页查询参数,而非构造分页查询条件查询器
        return obj;
    }

    /**
     * 发布一个贴子: 新增Question
     *
     * @param req
     * @return
     * @throws Exception
     */
    @DeleteRedisCachePageObject
    @Override
    public String add(AddQuestionReq req) throws Exception {
        UserViewModel user = checkLogin();
        String id = UUID.randomUUID().toString();
        // AddQuestionReq => Question
        Question question = mapper.map(req, Question.class);
        question.setUserId(user.getId());
        question.setCreateTime(TimestampUtil.getTimestampNow());
        question.setId(id);
        save(question);
        return id;
    }

    /**
     * 修改贴子: 修改Question
     *
     * @param req
     * @return
     * @throws Exception
     */
    @DeleteRedisCachePageObject
    @Override
    public String edit(AddQuestionReq req) throws Exception {

        UserViewModel loginInfo = checkLogin();

        Question question = mapper.map(req, Question.class);
        saveOrUpdate(question);
        return question.getId();
    }

    /**
     * 删除帖子: 删除Question
     *
     * @param id
     * @throws Exception
     */
    @DeleteRedisCachePageObject
    @Override
    public void del(String id) throws Exception {
        UserViewModel user = checkLogin();
        // 判断权限
        if (user.getAuth() != 1) {
            throw new Exception("哇偶！快联系站长删除");
        }
        removeById(id);
    }

    /**
     * 管理员设置贴子的一些属性值, 比如置顶状态、精华帖
     *
     * @param id
     * @param field
     * @param rank
     * @throws Exception
     */
    @DeleteRedisCachePageObject
    @Override
    public void set(String id, String field, int rank) throws Exception {
        UserViewModel user = checkLogin();
        // 权限不足
        if (user.getAuth() != 1 && user.getAuth() != 2) {
            throw new Exception("哇偶！这是站长那帮人干的事");
        }

        QuestionExample exp = new QuestionExample();
        QuestionExample.Criteria criteria = exp.createCriteria();
        criteria.andIdEqualTo(id);

        Question question = getById(id);

        if (STICK.equals(field)) {  //置顶
            question.setStick(rank);
        } else if (STATUS.equals(field)) { //精贴
            question.setStatus(rank);
        }
        // question: 修改内容
        // exp: 模糊
        questionDao.updateByExampleSelective(question, exp);

    }

    /**
     * 删除回答
     *
     * @param id
     * @throws Exception
     */
    @DeleteRedisCachePageObject
    @Override
    public void delAnswer(String id) throws Exception {
        UserViewModel user = checkLogin();
        // 只有管理员有权限删除
        if (user.getAuth() != 1) {
            throw new Exception("哇偶！快联系站长删除");
        }

        answerDao.deleteById(id);
    }

    /**
     * 采纳回答
     *
     * @param id
     * @throws Exception
     */
    @DeleteRedisCachePageObject
    @Override
    public void accept(String id) throws Exception {
        UserViewModel loginUser = checkLogin();
        Answer answer = answerDao.selectById(id);

        if (loginUser.getAuth() != 1 && loginUser.getAuth() != 2 && answer.getUserId() == loginUser.getId()) {
            throw new Exception("哇偶！你无权干涉这个问题");
        }

        Question question = getById(answer.getAnswerTo());
        question.setAccept(answer.getId());
        updateById(question);

        User user = userDao.selectById(loginUser.getId());
        user.setExperience(loginUser.getExperience() + question.getExperience());
        userDao.updateById(user);
    }

    /**
     * 获取用户发的贴子
     *
     * @param request
     * @return
     */
    @Override
    public PageObject<QuestionViewModel> getByUser(QueryQuestionsByUser request) {
        int index = request.getIndex(), size = request.getSize();
        if (index == 0) {
            index = 1;
        }
        if (size == 0) {
            size = 10;
        }

        QuestionExample exp = new QuestionExample();
        exp.setOrderByClause("time desc");

        QuestionExample.Criteria criteria = exp.createCriteria();
        criteria.andUseridEqualTo(request.getUid());

        PageObject<QuestionViewModel> obj = new PageObject<>();
        obj.setSize(size);

        obj.setTotal(questionDao.countByExample(exp));

        int startindex = (index - 1) * size;
        exp.setOffset(startindex);
        exp.setLimit(size);

        obj.setObjects(questionDao.getQuestionVMs(exp));

        return obj;
    }

    /**
     * 获取用户回答过的问题列表
     *
     * @param req
     * @return
     */
    @Override
    public PageObject<QuestionViewModel> getByUserAnswer(QueryQuestionsByUser req) {

        PageObject<QuestionViewModel> obj = new PageObject<>();
        Page<QuestionViewModel> questionPage = questionDao.getUserAnswered(
                new Page<>(req.getIndex(), req.getSize()), req.getUid());

        obj.setSize(req.getSize());
        obj.setTotal((int) questionPage.getTotal());
        obj.setObjects(questionPage.getRecords());

        return obj;
    }

    /**
     * 获取某个具体问题的回答
     *
     * @param req
     * @return
     * @throws NormalException
     */
    @Override
    public PageObject<AnswerViewModel> queryAnswersOfQuestion(QueryAnswersOfQuestionReq req) throws NormalException {
        if (Strings.isNullOrEmpty(req.getJid())) {
            throw new NormalException("问题ID不能为空");
        }
        PageObject<AnswerViewModel> resp = new PageObject<>();
        Page<AnswerViewModel> questionPage = answerDao.queryAnswersOfQuestion(new Page<>(req.getIndex(), req.getSize()), req.getJid());

        resp.setSize(req.getSize());
        resp.setTotal((int) questionPage.getTotal());
        resp.setObjects(questionPage.getRecords());

        return resp;
    }

    /**
     * 获取热门帖子
     *
     * @return
     */
    @RedisCachePageObject
    @Override
    public PageObject<QuestionViewModel> getHot() {
        int index = 1;
        int size = 10;
        QuestionExample exp = new QuestionExample();
        exp.setOrderByClause("hits desc,time desc");

        PageObject<QuestionViewModel> obj = new PageObject<>();
        obj.setSize(size);

        obj.setTotal(questionDao.countByExample(exp));

        int startindex = (index - 1) * size;
        exp.setOffset(startindex);
        exp.setLimit(size);

        obj.setObjects(questionDao.getQuestionVMs(exp));

        return obj;
    }

    /**
     * 新增回答
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public String addAnswer(AddAnswerRequest request) throws Exception {
        UserViewModel loginUser = checkLogin();
        String id = addAnswer(request.getJid(), request.getContent(), loginUser);

        User user = userDao.selectById(loginUser.getId());
        user.setAnswerCnt(loginUser.getAnswerCnt() + 1);
        userDao.updateById(user);

        QuestionExample exp = new QuestionExample();
        QuestionExample.Criteria criteria = exp.createCriteria();
        criteria.andIdEqualTo(request.getJid());

        Question question = getById(request.getJid());
        question.setComment(question.getComment() + 1);
        questionDao.updateByExampleSelective(question, exp);

        msgService.notify(user, question);
        return id;

    }


    /**
     * get
     *
     * @param id
     * @return
     */
    @Override
    public QuestionViewModel get(String id) {
        QuestionViewModel q = questionDao.getQuestionVM(id);
        addHitCnt(id);
        return q;
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
    public List<Question> findPage(Question condition, Integer pageNum, Integer pageSize) {
        PageInfo<Question> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            questionDao.selectList(Wrappers.<Question>query(condition));
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
    public List<Question> find(Question condition) {
        return questionDao.selectList(Wrappers.query(condition));
    }

    /**
     * 添加
     *
     * @param question
     * @return
     */
/*    @Override
    public Integer add(Question question) {
        return questionDao.insert(question);
    }*/

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @RedisCache
    @Override
    public Question findById(String id) {
        return questionDao.selectById(id);
    }

    /**
     * 修改
     *
     * @param question
     * @return
     */
    @Override
    public Integer update(Question question) {
        return questionDao.updateById(question);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return questionDao.deleteById(id);
    }
}
