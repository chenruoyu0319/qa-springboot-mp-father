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
 * @Description:
 * @Date Created in:  2021-01-20 16:41
 * @Modified By:
 */
@ApiModel(value = "com.cry.qa.domain.Message", description = "信息表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("message")
public class Message implements Serializable {

    @ApiModelProperty(value = "信息id")
    @NonNull
    @TableId(value = "id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @NonNull
    @TableField(value = "time", fill = FieldFill.INSERT)
    private Timestamp insertTime;

    @ApiModelProperty(value = "发送内容")
    @NonNull
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(value = "链接")
    @TableField(value = "href")
    private String href;

    @ApiModelProperty(value = "发送用户id")
    @NonNull
    @TableField(value = "msg_from")
    private String msgFrom;

    @ApiModelProperty(value = "接收用户id")
    @NonNull
    @TableField(value = "msg_to")
    private String msgTo;

    @ApiModelProperty(value = "来源方状态")
    @NonNull
    @TableField(value = "fromstatus")
    private Integer fromStatus;

    @ApiModelProperty(value = "到达方状态")
    @TableField(value = "tostatus")
    private Integer toStatus;

    @ApiModelProperty(value = "消息类型")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty(value = "来源方昵称")
    @TableField(value = "fromname")
    private String fromName;

    @ApiModelProperty(value = "到达方昵称")
    @TableField(value = "toname")
    private String toName;

    @ApiModelProperty(value = "信息阅读量")
    @NonNull
    @TableField(value = "msg_read")
    private Integer msgRead;

    @ApiModelProperty(value = "信息标题")
    @TableField(value = "title")
    private String title;

    /**
     * 逻辑删除（0 未删除、1 删除）
     */
    @ApiModelProperty(value = "逻辑删除（不显示给用户）")
    @NonNull
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Short deleteFlag;

    public Message(@NonNull String id, @NonNull String content, String href, @NonNull String msgFrom, @NonNull String msgTo, @NonNull Integer fromStatus, Integer toStatus, String type, String fromName, String toName, @NonNull Integer msgRead, String title) {
        this.id = id;
        this.content = content;
        this.href = href;
        this.msgFrom = msgFrom;
        this.msgTo = msgTo;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.type = type;
        this.fromName = fromName;
        this.toName = toName;
        this.msgRead = msgRead;
        this.title = title;
    }

    public Message(@NonNull String id, String title) {
        this.id = id;
        this.title = title;
    }
}
