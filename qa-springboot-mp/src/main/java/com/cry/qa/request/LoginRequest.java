package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录请求类, 封装了属性: 账号、密码
 */
@ApiModel(value = "com.cry.qa.request.LoginRequest", description = "登录请求封装类")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {

    @ApiModelProperty(value = "用户登录账号")
    private String account;
    @ApiModelProperty(value = "用户登录密码")
    private  String pwd;

    public LoginRequest() {
    }

    public LoginRequest(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
