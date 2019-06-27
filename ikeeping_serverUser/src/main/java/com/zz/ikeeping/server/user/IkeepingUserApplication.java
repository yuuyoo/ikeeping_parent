package com.zz.ikeeping.server.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement  //开启事务管理
@EnableEurekaClient  //服务提供者
@MapperScan("com.zz.ikeeping.server.user.dao")  //扫描Dao
public class IkeepingUserApplication  extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(IkeepingUserApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(IkeepingUserApplication.class);
    }
}
