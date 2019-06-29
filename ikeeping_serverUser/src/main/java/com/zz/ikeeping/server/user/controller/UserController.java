package com.zz.ikeeping.server.user.controller;

import com.zz.ikeeping.common.exception.UserException;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;

import com.zz.ikeeping.server.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // 新增
    @PostMapping("user/save.do")
    public R save(@RequestBody User user) throws UserException {
        return userService.save(user);
    }
    
    // 查询手机号是否被注册
    @GetMapping("user/checkphone.do")
    public R check(@RequestParam("phone") String phone){
        return userService.checkPhone(phone);
    }


    // 验证码注册
    @GetMapping("user/verifyCode.do")
    public R verifyCode(@RequestParam("phone") String phone, @RequestParam("code") int code) {
        return userService.insert(phone, code);
    }


}

