package com.cry.qa.config;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-02-19 0:36
 * @Modified By:
 */
public class RedisConfig2 {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig2.class);

    //常量声明时不马上初始化
    public static final String CONFIG;

    static {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            //通过类加载器进行获取properties文件流
            in = PropertyUtil.class.getClassLoader().getResourceAsStream("redis.properties");
            properties.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        CONFIG = properties.getProperty("redis.pointcut");
        logger.info("properties文件内容：" + CONFIG);
    }
}
