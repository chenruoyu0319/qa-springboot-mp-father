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
 * @Description:
 * @Date Created in:  2021-01-20 16:46
 * @Modified By:
 */
@ApiModel(value = "com.cry.qa.domain.Question", description = "Question表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("question")
public class Question implements Serializable {

    @ApiModelProperty(value = "Question-id")
    @NonNull
    @TableId(value = "Id")
    private String id;

    @ApiModelProperty(value = "Question标题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty(value = "Question内容")
    @TableField(value = "Content")
    private String content;

    @ApiModelProperty(value = "顶Num")
    @TableField(value = "SupportCnt")
    private Integer supportCnt;

    @ApiModelProperty(value = "踩Num")
    @TableField(value = "OpposeCnt")
    private Integer opposeCnt;

    @ApiModelProperty(value = "被评论的对象")
    @TableField(value = "CommentTo")
    private String commentTo;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "time", fill = FieldFill.INSERT)
    private Timestamp createTime;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "UserId")
    private String userId;
    @NonNull

    @ApiModelProperty(value = "浏览量")
    @TableField(value = "hits")
    private Integer hits;

    @ApiModelProperty(value = "置顶数")
    @NonNull
    @TableField(value = "stick")
    private Integer stick;

    @ApiModelProperty(value = "回答数")
    @NonNull
    @TableField(value = "comment")
    private Integer comment;

    @ApiModelProperty(value = "加精华")
    @NonNull
    @TableField(value = "status")
    private Integer status;

    @ApiModelProperty(value = "悬赏")
    @NonNull
    @TableField(value = "experience")
    private Integer experience;

    @ApiModelProperty(value = "是否采纳")
    @TableField(value = "accept")
    private String accept;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public Question(@NonNull String id, String title, String content, Integer supportCnt, Integer opposeCnt, String commentTo, String userId, @NonNull Integer hits, @NonNull Integer stick, @NonNull Integer comment, @NonNull Integer status, @NonNull Integer experience, String accept) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.supportCnt = supportCnt;
        this.opposeCnt = opposeCnt;
        this.commentTo = commentTo;
        this.userId = userId;
        this.hits = hits;
        this.stick = stick;
        this.comment = comment;
        this.status = status;
        this.experience = experience;
        this.accept = accept;
    }
}
