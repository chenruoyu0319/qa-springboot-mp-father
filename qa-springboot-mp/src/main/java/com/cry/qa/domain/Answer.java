package com.cry.qa.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: Chen ruoyu
 * @Description: answer表
 * @Date Created in:  2021-01-20 16:10
 * @Modified By:
 */
@ApiModel(value = "com.cry.qa.domain.Answer", description = "Answer表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "answer")
public class Answer implements Serializable {

    @ApiModelProperty(value = "Answer-id")
    @NonNull
    @TableId(value = "Id")
    private String id;

    @ApiModelProperty(value = "Answer内容")
    @TableField(value = "Content")
    private String content;

    @ApiModelProperty(value = "顶Num")
    @TableField(value = "SupportCnt")
    private Integer supportCnt;

    @ApiModelProperty(value = "踩Num")
    @TableField(value = "OpposeCnt")
    private Integer opposeCnt;

    @ApiModelProperty(value = "回复对象")
    @TableField(value = "AnswerTo")
    private String answerTo;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "time", fill = FieldFill.INSERT)
    private Timestamp insertTime;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "UserId")
    private String userId;

    @ApiModelProperty(value = "是否采纳")
    @NonNull
    @TableField(value = "accept")
    private Integer accept;

    @ApiModelProperty(value = "好评")
    @NonNull
    @TableField(value = "praise")
    private Integer praise;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public Answer(@NonNull String id, String content, Integer supportCnt, Integer opposeCnt, String answerTo, String userId, Integer accept, Integer praise) {
        this.id = id;
        this.content = content;
        this.supportCnt = supportCnt;
        this.opposeCnt = opposeCnt;
        this.answerTo = answerTo;
        this.userId = userId;
        this.accept = accept;
        this.praise = praise;
    }

}

