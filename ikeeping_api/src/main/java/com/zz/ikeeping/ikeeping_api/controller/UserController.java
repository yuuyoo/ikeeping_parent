package com.zz.ikeeping.ikeeping_api.controller;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import com.zz.ikeeping.ikeeping_api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(value = "用户注册接口", tags = "用户注册接口")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //普通新增用户
    @ApiOperation("普通新增用户")
    @PostMapping("/api/user/save.do")
    public R save(@RequestBody User user){
        return userService.save(user);
    }
    // 查询用户有没有注册过
    @ApiOperation("查询用户有没有注册过")
    @GetMapping("api/user/checkphone.do")
    public R check(@RequestParam("phone") String phone){
        return userService.check(phone);
    }
    // 根据手机验证码注册
    @ApiOperation("根据手机验证码注册")
    @GetMapping("api/user/verifyCode.do")
    public R verifyCode(@RequestParam("phone") String phone, @RequestParam("code") int code) {
        return userService.verifyCode(phone, code);
    }

}
