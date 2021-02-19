package com.cry.token.domain;

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
 * @Date Created in:  2021-01-20 17:03
 * @Modified By:
 */
@ApiModel(value = "com.cry.token.domain.User", description = "用户表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User implements Serializable {

    @ApiModelProperty(value = "用户id")
    @NonNull
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "账号")
    @NonNull
    @TableField(value = "account")
    private String account;

    @ApiModelProperty(value = "昵称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "密码")
    @NonNull
    @TableField(value = "pwd")
    private String pwd;

    @ApiModelProperty(value = "VIP等级")
    @NonNull
    @TableField(value = "rmb")
    private Integer rmb;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @NonNull
    @TableField(value = "createtime", fill = FieldFill.INSERT)
    private Timestamp createTime;

    @ApiModelProperty(value = "登录时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "logintime", fill = FieldFill.UPDATE)
    private Timestamp loginTime;

    @ApiModelProperty(value = "关联微信账号")
    @TableField(value = "wxopenid")
    private String wxOpenid;

    @ApiModelProperty(value = "关联QQ账号")
    @TableField(value = "qqopenid")
    private String qqOpenid;

    @ApiModelProperty(value = "question条数")
    @NonNull
    @TableField(value = "questioncnt")
    private Integer questionCnt;

    @ApiModelProperty(value = "answer条数")
    @NonNull
    @TableField(value = "answercnt")
    private Integer answerCnt;

    @ApiModelProperty(value = "头像")
    @TableField(value = "pic")
    private String pic;

    @ApiModelProperty(value = "用户权限")
    @NonNull
    @TableField(value = "auth")
    private Integer auth;

    @ApiModelProperty(value = "经验值")
    @NonNull
    @TableField(value = "experience")
    private Integer experience;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public User(@NonNull String id, @NonNull String account, String name, @NonNull String pwd, @NonNull Integer rmb, String wxOpenid, String qqOpenid, @NonNull Integer questionCnt, @NonNull Integer answerCnt, String pic, @NonNull Integer auth, @NonNull Integer experience) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.pwd = pwd;
        this.rmb = rmb;
        this.wxOpenid = wxOpenid;
        this.qqOpenid = qqOpenid;
        this.questionCnt = questionCnt;
        this.answerCnt = answerCnt;
        this.pic = pic;
        this.auth = auth;
        this.experience = experience;
    }
}
