package com.cry.token.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-27 18:22
 * @Modified By:
 */
@ApiModel(value = "com.cry.token.domain.Token", description = "Token持久化表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "token")
public class Token implements Serializable {

    @ApiModelProperty(value = "用户id")
    @NonNull
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "token")
    @NonNull
    @TableField(value = "token")
    private String token;

    @ApiModelProperty(value = "tokenValue")
    @NonNull
    @TableField(value = "token_value")
    private String tokenValue;

}
