package com.zz.ikeeping.service.login.config;


import com.zz.ikeeping.common.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class IdGeneratorConfig {
    @Bean
    public IdGenerator createIG(){
        return new IdGenerator();
    }
}
