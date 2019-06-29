package com.zz.ikeeping.api.login.controller;

import com.zz.ikeeping.api.login.service.LoginService;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.vo.R;

import com.zz.ikeeping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
//    @Autowired
//    private UpdateService updateService;
    //登录
    @PostMapping("/api/login/login.do")
    public R login(String phone, String password){
        return loginService.login(phone, password);
    }
    //检查是否有效
    @GetMapping("api/login/checklogin.do")
    public R check(HttpServletRequest request){
        return loginService.check(request.getHeader(ProjectConfig.TOKENHEAD));
    }
    //注销
    @GetMapping("/login/exit.do")
    public R exit(HttpServletRequest request){
        return loginService.exit(request.getHeader(ProjectConfig.TOKENHEAD));
    }
    //更新用户信息
    @PostMapping("/api/user/update.do")
    public R udate(@RequestBody User user){
        return loginService.update(user);
    }
}
