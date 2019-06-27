package com.zz.ikeeping.api.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author DJL
 * @create 2019-06-26 20:47
 * @desc swagger配置类
 **/
@Configuration
public class SwaggerConfig {

    public ApiInfo createA(){
        ApiInfo info=new ApiInfoBuilder().title("统一资源管理平台").
                contact( new Contact("DJL","https://github.com/yuuyoo/ikeeping_parent","yuuyoo@163.com")).
                description("实现文件上传功能").build();
        return info;
    }

    @Bean
    public Docket createDocket(){
        Docket docket=new Docket(DocumentationType.SWAGGER_2).apiInfo(createA()).select().
                apis(RequestHandlerSelectors.basePackage("com.zz.ikeeping.api.resource.controller")).
                build();
        return docket;
    }
}
