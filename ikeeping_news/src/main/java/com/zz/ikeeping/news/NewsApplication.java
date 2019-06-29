package com.zz.ikeeping.news;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.zz.ikeeping.news.dao")
@EnableSwagger2
public class NewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsApplication.class,args);
    }
}
