package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMsgReq {

    @ApiModelProperty(value = "接收用户id")
    private String msgTo;
    @ApiModelProperty(value = "发送内容")
    private String content;

}
