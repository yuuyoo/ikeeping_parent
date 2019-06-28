package com.zz.ikeeping.api.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DJL
 * @create 2019-06-26 20:34
 * @desc ${DESCRIPTION}
 **/
@SpringBootApplication
@EnableSwagger2
public class ResourceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ResourceApplication.class);
    }
}
