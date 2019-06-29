package com.zz.ikeeping.service.login.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import com.zz.ikeeping.service.login.service.LoginService;
import com.zz.ikeeping.service.login.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserUpdateService updateService;

    //登录
    @PostMapping("/login/login.do")
    public R login(@RequestParam("phone")String phone, @RequestParam("password")String password){
        return loginService.login(phone, password);
    }
    //检查是否有效
    @GetMapping("/login/checklogin.do")
    public R check(@RequestParam("token")String token){
        return loginService.checkLogin(token);
    }
    //注销
    @GetMapping("/login/exit.do")
    public R exit(@RequestParam("token")String token){
        return loginService.exitLogin(token);
    }
    //更新用户信息
    @PostMapping("/user/update.do")
    public R update(@RequestBody User user){
        return updateService.UserInfoUpdate(user);
    }
}
