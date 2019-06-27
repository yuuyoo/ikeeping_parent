package com.zz.ikeeping.api.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration //标记这是一个配置
public class SwaggerConfig {
    public ApiInfo createA(){
        ApiInfo info = new ApiInfoBuilder().title("统一登录鉴权中心-数据接口平台").
                contact(new Contact("nick","https://github.com/yuuyoo/ikeeping_parent","975270653@qq.com")).
                description("实现统一登录鉴权中心").build();
        return info;
    }
    /*
    * 创建AOPI应用
    * appinfo()增加API相关信息
    * 通过select()函数返回一个ApiSelectorBuilder实例
    * 用来控制那些接口暴露给Swagger来展现
    * 采用置顶扫描的包路径来定意思置顶的要建立API的目录
    * */
    @Bean
    public Docket createDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(createA()).select().
                apis(RequestHandlerSelectors.basePackage("com.zz.ikeeping.api.login.controller")).
                build();
        return docket;
    }
}
