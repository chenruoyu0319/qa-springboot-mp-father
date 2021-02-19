package com.cry.qa.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddAnswerRequest {

    @ApiModelProperty(value = "贴子的ID")
    private  String jid;
    @ApiModelProperty(value="回答的内容")
    private  String content;

    public void setJid(String jid) {
        this.jid = jid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJid() {
        return jid;
    }

    public String getContent() {
        return content;
    }
}
