package com.zz.ikeeping.service.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zz.ikeeping.service.login.dao")
public class LoginApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApiApplication.class,args);
    }
}
