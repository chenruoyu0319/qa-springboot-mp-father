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
 * @Date Created in:  2021-01-20 17:01
 * @Modified By:
 */
@ApiModel(value = "com.cry.qa.domain.UpdateLog", description = "更新日志表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "updatelog")
public class UpdateLog implements Serializable {

    @ApiModelProperty(value = "更新日志id")
    @NonNull
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "描述信息")
    @NonNull
    @TableField(value = "description")
    private String description;

    @ApiModelProperty(value = "版本号")
    @NonNull
    @TableField(value = "version")
    private String version;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */

    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public UpdateLog(@NonNull String id, @NonNull String description, @NonNull String version) {
        this.id = id;
        this.description = description;
        this.version = version;
    }
}
