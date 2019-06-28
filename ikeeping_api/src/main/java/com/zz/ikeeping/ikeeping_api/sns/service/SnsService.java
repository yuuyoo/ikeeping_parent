package com.zz.ikeeping.ikeeping_api.sns.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "UserProvider")
public interface SnsService {

    //@GetMapping("sns/showTopicType.do")
    //JsonBean selectTopicType();
}
