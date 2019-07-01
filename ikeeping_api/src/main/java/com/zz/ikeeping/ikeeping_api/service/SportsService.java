package com.zz.ikeeping.ikeeping_api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "SportsProvider")
public interface SportsService {
    /**
     * 计算BMI指数
     * @param weight
     * @param height
     * @return
     */
    @GetMapping("sports/calculate.do")
    public String calBMI(@RequestParam("weight") float weight, @RequestParam("height") float height);
}
