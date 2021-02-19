package com.cry.qa.domain;

import com.baomidou.mybatisplus.annotation.*;
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
 * @Date Created in:  2021-01-20 16:56
 * @Modified By:
 */
@ApiModel(value = "com.cry.qa.domain.SysConf", description = "系统配置表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sysconf")
public class SysConf implements Serializable {

    @ApiModelProperty(value = "APP配置id")
    @NonNull
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "版本号的值/下载次数的值")
    @NonNull
    @TableField(value = "sys_value")
    private String sysValue;

    @ApiModelProperty(value = "版本号/下载次数")
    @NonNull
    @TableField(value = "sys_key")
    private String sysKey;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public SysConf(@NonNull String id, @NonNull String sysValue, @NonNull String sysKey) {
        this.id = id;
        this.sysValue = sysValue;
        this.sysKey = sysKey;
    }
}
