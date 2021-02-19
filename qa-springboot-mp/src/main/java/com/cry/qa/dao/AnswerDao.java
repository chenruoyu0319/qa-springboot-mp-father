package com.cry.qa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.qa.domain.Answer;
import com.cry.qa.response.AnswerViewModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-20 17:11
 * @Modified By:
 */
@Repository
public interface AnswerDao extends BaseMapper<Answer> {

    /**
     * 查询对Question的所有Answer
     *
     * @param answerViewModelPage
     * @param answerTo
     * @return
     */
    Page<AnswerViewModel> queryAnswersOfQuestion(Page<AnswerViewModel> answerViewModelPage, @Param("answerTo") String answerTo);
}
