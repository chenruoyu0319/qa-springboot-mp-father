package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "com.cry.qa.request.MsgListReq", description = "Msg分页查询请求封装类")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgListReq {

    @ApiModelProperty(value = "搜索关键字")
    private String key;
    @ApiModelProperty(value = "页码")
    private int index;
    @ApiModelProperty(value = "每页行数")
    private int size;

    // 无参构造赋初值
    public MsgListReq() {
        this.key = null;
        this.index = 1;
        this.size = 10;
    }


}
