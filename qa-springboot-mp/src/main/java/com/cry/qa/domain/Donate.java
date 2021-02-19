package com.cry.qa.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: Chen ruoyu
 * @Description: donate表
 * @Date Created in:  2021-01-20 16:39
 * @Modified By:
 */
@ApiModel(value = "com.cry.qa.domain.Donate", description = "打赏表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("donate")
public class Donate implements Serializable {

    @ApiModelProperty(value = "打赏id")
    @NonNull
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @NonNull
    @TableField(value = "time", fill = FieldFill.INSERT)
    private Timestamp insertTime;

    @ApiModelProperty(value = "打赏金额")
    @NonNull
    @TableField(value = "money")
    private Float money;

    @ApiModelProperty(value = "支付方式")
    @NonNull
    @TableField(value = "type")
    private String type;

    @ApiModelProperty(value = "用户昵称")
    @NonNull
    @TableField(value = "name")
    private String name;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public Donate(@NonNull String id, @NonNull Float money, @NonNull String type, @NonNull String name) {
        this.id = id;
        this.money = money;
        this.type = type;
        this.name = name;
    }
}
