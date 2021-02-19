package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class QueryQuestionsByUser {
    @ApiModelProperty(value = "用户id")
    private  String uid;
    @ApiModelProperty(value = "页码")
    private  int index;
    @ApiModelProperty(value = "数量")
    private  int size;

    public QueryQuestionsByUser() {
        size = 0;
        index = 0;
        uid = null;
    }


}
