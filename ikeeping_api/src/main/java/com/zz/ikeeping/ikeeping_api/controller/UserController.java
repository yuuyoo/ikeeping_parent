package com.zz.ikeeping.ikeeping_api.controller;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import com.zz.ikeeping.ikeeping_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //新增
    @PostMapping("/api/user/save.do")
    public R save(@RequestBody User user){
        return userService.save(user);
    }
    @GetMapping("api/user/checkphone.do")
    public R check(@RequestParam("phone") String phone){
        return userService.check(phone);
    }
    @GetMapping("api/user/verifyCode.do")
    public R verifyCode(@RequestParam("phone") String phone, @RequestParam("code") int code) {
        return userService.verifyCode(phone, code);
    }
    @GetMapping("api/user/sendcode.do")
    public R sendCode(@RequestParam("phone") String phone) {
        return userService.sendCode(phone);
    }

}
