package com.cry.qa.http;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * HTTP结果封装
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@ApiModel(value = "com.cry.qa.http.HttpResult")
public class HttpResult<T> implements Serializable {

    @ApiModelProperty(value = "响应码")
    private int code = 200;
    @ApiModelProperty(value = "响应信息")
    private String msg;
    @ApiModelProperty(value = "响应体")
    private Object data;

    public static HttpResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常,请联系管理员");
    }

    public static HttpResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(Object data) {
/*		HttpResult r = new HttpResult();
		r.setData(data);
		return r;*/
        return ok(data, "操作成功");
    }

    public static HttpResult ok(Object data, String msg) {
        HttpResult r = new HttpResult();
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok() {
        return new HttpResult();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
