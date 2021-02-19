package com.cry.qa;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author: Chen ruoyu
 * @Description: 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 * @Date Created in:  2021-02-10 14:30
 * @Modified By:
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里一定要指向原先用main方法执行的Application启动类
        return builder.sources(QaApplication.class);
    }
}