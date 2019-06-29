package com.zz.ikeeping.news.config;

import com.zz.ikeeping.common.util.JedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
@Bean
public JedisUtil createJu(){
    return JedisUtil.getInstance();
}
}
