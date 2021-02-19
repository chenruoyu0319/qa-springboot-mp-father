package com.cry.qa.config;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-01-22 14:27
 * @Modified By:
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * swagger文档生成配置
 */
@Configuration
//@EnableSwagger2
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                //swagger搜索的包
                .apis(RequestHandlerSelectors.basePackage("com.cry.qa.controller"))
                //swagger路径匹配
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo() {
        return new ApiInfoBuilder()
                .title("Question-Answer项目接口文档")
                .description("使用RestFul风格的Controller接口")
                //.termsOfServiceUrl("http://www.xx.com/")
                //.contact("group@qq.com")
                .version("1.0")
                .build();
    }
}

