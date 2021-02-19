package com.cry.qa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.qa.domain.Question;
import com.cry.qa.domain.QuestionExample;
import com.cry.qa.response.QuestionViewModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-20 17:11
 * @Modified By:
 */
@Repository
public interface QuestionDao extends BaseMapper<Question> {

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    QuestionViewModel getQuestionVM(String id);

    /**
     * 根据复杂查询条件QuestionExample查询QuestionViewModel
     *
     * @param example
     * @return
     */
    List<QuestionViewModel> getQuestionVMs(QuestionExample example);

    /**
     * 根据复杂查询条件QuestionExample计数
     *
     * @param example
     * @return
     */
    int countByExample(QuestionExample example);

    /**
     * 动态查询
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    /**
     * 分页查询UserAnswer
     * @param questionViewModelPage
     * @param uid
     * @return
     */
    Page<QuestionViewModel> getUserAnswered(Page<QuestionViewModel> questionViewModelPage, @Param("uid") String uid);
}
