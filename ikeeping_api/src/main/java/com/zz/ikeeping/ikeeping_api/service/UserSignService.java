package com.zz.ikeeping.ikeeping_api.service;


import com.zz.ikeeping.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserProvider")
public interface UserSignService {
    @GetMapping("usersign/all.do")
    R all(@RequestParam("token") String token);
    @GetMapping("/usersign/single.do")
    R single(String token);
    @GetMapping("/usersign/")
    R signDays(@RequestParam("token") String token, @RequestParam("days") int days);
    @PostMapping("/usersign/signsave.do")
    R save(@RequestParam("token") String token);
}
