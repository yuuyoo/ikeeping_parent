package com.zz.ikeeping.ikeeping_api.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserProvider")
public interface UserService {
    //新增
    @PostMapping("user/save.do")
    R save(@RequestBody User user);
    //校验手机号是否存在
    @GetMapping("user/checkphone.do")
    R check(@RequestParam("phone") String phone);
    @GetMapping("user/verifyCode.do")
    R verifyCode(@RequestParam("phone") String phone, @RequestParam("code") int code);
    @GetMapping("user/sendcode.do")
    R sendCode(@RequestParam("phone") String phone);
}


