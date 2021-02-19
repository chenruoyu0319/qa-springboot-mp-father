package com.cry.qa.response;


import com.cry.qa.domain.Question;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016-12-05.
 */
public class QuestionViewModel extends Question {

    /**
     * 发布者
     */
    @ApiModelProperty(value = "多方存一方的对象")
    public UserViewModel user;
}
