package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAnswersOfQuestionReq {
    @ApiModelProperty(value = "问题id")
    private String jid;
    @ApiModelProperty(value = "页码")
    private int index;
    @ApiModelProperty(value = "数量")
    private int size;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public QueryAnswersOfQuestionReq() {
        size = 0;
        index = 0;
        jid = null;
    }


}
