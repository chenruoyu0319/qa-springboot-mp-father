package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "com.cry.qa.request.QuestionListReq", description = "Question分页查询请求封装类")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class QuestionListReq {

    @ApiModelProperty(value = "搜索关键字")
    private String key;
    @ApiModelProperty(value = "Question类型:   resolved: 已解决/unresolved: 未解决/wonderful: 精华帖")
    private String type;
    @ApiModelProperty(value = "页码")
    private int index;
    @ApiModelProperty(value = "每页行数")
    private int size;
}
