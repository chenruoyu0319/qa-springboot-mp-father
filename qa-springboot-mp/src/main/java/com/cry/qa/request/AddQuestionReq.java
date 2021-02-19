package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@ApiModel(value = "com.cry.qa.request.AddQuestionReq", description = "Question表请求参数封装表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddQuestionReq implements Serializable {

    @ApiModelProperty(value = "Question-id")
    private String id;

    @ApiModelProperty(value = "Question标题")
    private String title;

    @ApiModelProperty(value = "Question内容")
    private String content;

    @ApiModelProperty(value = "顶Num")
    private Integer supportCnt;

    @ApiModelProperty(value = "踩Num")
    private Integer opposeCnt;

    @ApiModelProperty(value = "被评论的对象")
    private String commentTo;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "浏览量")
    private Integer hits;

    @ApiModelProperty(value = "置顶数")
    private Integer stick;

    @ApiModelProperty(value = "回答数")
    private Integer comment;

    @ApiModelProperty(value = "加精华")
    private Integer status;

    @ApiModelProperty(value = "悬赏")
    private Integer experience;

    @ApiModelProperty(value = "是否采纳")
    private String accept;

}