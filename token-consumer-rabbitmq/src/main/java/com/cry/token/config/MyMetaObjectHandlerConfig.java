package com.cry.token.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.cry.token.utils.TimestampUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-21 17:37
 * @Modified By:
 */
@Component
public class MyMetaObjectHandlerConfig implements MetaObjectHandler {

    private final Short DELETEFLAGFIELDVAL = 0;

    @Override
    public void insertFill(MetaObject metaObject) {
        // Insert时自动填充deleteFlag字段, 默认值为0
        this.strictInsertFill(metaObject, "deleteFlag", Short.class, DELETEFLAGFIELDVAL);
        // Insert时自动填充insertTime字段, 默认值为当前时间
        this.strictInsertFill(metaObject, "insertTime", Timestamp.class, TimestampUtil.getTimestampNow());
        // Insert时自动填充createTime字段, 默认值为创建时间
        this.strictInsertFill(metaObject, "createTime", Timestamp.class, TimestampUtil.getTimestampNow());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // Update时自动填充createTime字段, 默认值为创建时间
        this.strictUpdateFill(metaObject, "loginTime", Timestamp.class, TimestampUtil.getTimestampNow());
    }

}
