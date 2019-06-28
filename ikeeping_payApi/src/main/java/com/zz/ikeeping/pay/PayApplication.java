package com.zz.ikeeping.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DJL
 * @create 2019-06-27 22:39
 * @desc ${DESCRIPTION}
 **/
@SpringBootApplication
@EnableSwagger2
public class PayApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PayApplication.class);
    }
}
