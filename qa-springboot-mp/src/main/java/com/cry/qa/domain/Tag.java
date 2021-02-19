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
 * @Date Created in:  2021-01-20 17:00
 * @Modified By:
 */
@ApiModel(value = "com.cry.qa.domain.Tag", description = "标签表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tag")
public class Tag implements Serializable {

    @ApiModelProperty(value = "标签id")
    @NonNull
    @TableId(value = "Id")
    private String id;

    @ApiModelProperty(value = "被打标签的对象")
    @TableField(value = "TagTo")
    private String tagTo;

    @ApiModelProperty(value = "标签名")
    @TableField(value = "Name")
    private String name;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */

    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public Tag(@NonNull String id, String tagTo, String name) {
        this.id = id;
        this.tagTo = tagTo;
        this.name = name;
    }
}