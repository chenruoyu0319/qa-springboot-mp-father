package com.cry.qa.response;

import com.cry.qa.domain.Answer;
import io.swagger.annotations.ApiModelProperty;

public class AnswerViewModel extends Answer {

    /**
     * 发布者
     */
    @ApiModelProperty(value = "多方存一方的对象")
    public UserViewModel user;
}
