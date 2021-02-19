package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "com.cry.qa.request.UpUserReq", description = "VIP升级")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpUserReq {

    @ApiModelProperty(value = "用户id")
    private String uid;
    @ApiModelProperty(value = "会员级别")
    private Integer rmb;

}
