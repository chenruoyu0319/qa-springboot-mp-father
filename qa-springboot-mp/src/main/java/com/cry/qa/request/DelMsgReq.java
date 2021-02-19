package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DelMsgReq {
    @ApiModelProperty(value = "消息id")
    private String id;

}
