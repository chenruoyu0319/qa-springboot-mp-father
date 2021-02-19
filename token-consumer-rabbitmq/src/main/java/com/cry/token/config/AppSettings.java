package com.cry.token.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="appsettings")
public class AppSettings {

    /**
     * nginx中, 上传的静态图片端口
     */
    private Integer picFilesPort;

    public Integer getPicfilesport() {
        return picFilesPort;
    }

    public void setPicfilesport(Integer picfilesport) {
        this.picFilesPort = picfilesport;
    }
}
