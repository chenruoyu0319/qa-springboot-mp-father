package com.cry.token.response;


import com.cry.token.utils.NormalException;
import com.cry.token.utils.ResultConstCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 分页结果实体类
 */
@ApiModel(value = "com.cry.token.response.PageObject", description = "查询的分页结果显示")
public class PageObject<T> {

    // 获取当前类的日志
    private final Logger logger = LoggerFactory.getLogger(PageObject.class);

    @ApiModelProperty(value = "总记录数")
    private int total;

    @ApiModelProperty(value = "总页数")
    private int pageCnt;

    @ApiModelProperty(value = "每页条数")
    private int size;

    @ApiModelProperty(value = "数据内容")
    private List<T> objects;

    @ApiModelProperty(value = "状态码,500为异常，其他可以自己定义")
    private int code;

    @ApiModelProperty(value = "消息,当code为200时可以忽略消息,当code为500等异常时,可查看该消息查找原因")
    private String msg;

    public int getTotal() {
        return total;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTotal(int total) {
        this.total = total;
        if(total == 0){
            this.pageCnt = 0;
        }else{
            this.pageCnt = total/size + 1;
        }
    }

    public void setError(NormalException error){
        logger.error(error.getMessage());
        setCode(error.getCode());
        setMsg(error.getMessage());
    }

    public PageObject(){
        msg ="操作成功";
        code = ResultConstCode.NORMAL;
    }

    @Override
    public String toString() {
        return "PageObject{" +
                "total=" + total +
                ", pageCnt=" + pageCnt +
                ", size=" + size +
                ", objects=" + objects +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
