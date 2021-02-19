package com.cry.qa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cry.qa.domain.SysConf;
import org.springframework.stereotype.Repository;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-20 17:11
 * @Modified By:
 */
@Repository
public interface SysConfDao extends BaseMapper<SysConf> {

    //如果采用sql的话，可以不用SqlSessionFactoryBean配置里的mapperLocations
    //@Select("SELECT * FROM sysconf  WHERE `key`='version' LIMIT 1")
    String getVersion();

    int getDownload();

    void addDownload();
}
