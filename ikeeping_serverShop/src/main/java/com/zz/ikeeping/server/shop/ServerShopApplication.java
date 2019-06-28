package com.zz.ikeeping.server.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author DJL
 * @create 2019-06-25 21:39
 * @desc ${DESCRIPTION}
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zz.ikeeping.server.shop.dao")  //扫描Dao
public class ServerShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerShopApplication.class, args);
    }
}
