package com.cry.token.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户注册类, 封装了属性: 账号、密码、姓名、密码验证
 */
@ApiModel(value = "com.cry.token.request.AddUserReq", description = "用户注册类")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserReq {

    @ApiModelProperty(value = "用户昵称/姓名")
    private String name;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "密码验证")
    private String checkPass;

    public AddUserReq() {
    }

    public AddUserReq(String name, String account, String pwd, String checkPass) {
        this.name = name;
        this.account = account;
        this.pwd = pwd;
        this.checkPass = checkPass;
    }

    public String getCheckPass() {
        return checkPass;
    }

    public void setCheckPass(String checkPass) {
        this.checkPass = checkPass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getPwd() {
        return pwd;
    }
}
