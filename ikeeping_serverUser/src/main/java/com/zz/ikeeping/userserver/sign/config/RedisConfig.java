package com.zz.ikeeping.server.user.config;


import com.zz.ikeeping.common.util.JedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisConfig {
    @Bean
    public JedisUtil createJU(){
        return JedisUtil.getInstance();
    }
}
