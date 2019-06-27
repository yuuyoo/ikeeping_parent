package com.zz.ikeeping.api.resource.config;

import com.zz.ikeeping.api.resource.util.KodoUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DJL
 * @create 2019-06-26 21:15
 * @desc 配置KodoUtil工具类对象
 **/
@Configuration
public class KodoUtilConfig {
    @Bean
    public KodoUtil createKodoUtil() {
        return new KodoUtil();
    }
}
