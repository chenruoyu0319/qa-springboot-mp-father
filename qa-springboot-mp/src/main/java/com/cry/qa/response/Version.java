package com.cry.qa.response;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-30 10:44
 * @Modified By:
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Version + Download
 */
@ApiModel(value = "com.cry.qa.response.Version", description = "Version和Download封装类")
public class Version {

    @ApiModelProperty(value = "下载次数")
    private int download;

    @ApiModelProperty(value = "版本号")
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }
}
