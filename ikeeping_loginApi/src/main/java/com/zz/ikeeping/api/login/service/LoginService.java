package com.zz.ikeeping.api.login.service;

import com.zz.ikeeping.common.vo.R;

import com.zz.ikeeping.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.rmi.server.UID;

@FeignClient(name = "LoginProvider")
public interface LoginService {
    //登录
    @PostMapping("/login/login.do")
    R login(@RequestParam("phone")String phone, @RequestParam("password")String password);
    //检查是否有效
    @GetMapping("/login/checklogin.do")
    R check(@RequestParam("token")String token);
    //注销
    @GetMapping("/login/exit.do")
    R exit(@RequestParam("token")String token);
    @PostMapping("/user/update.do")
    R update(@RequestBody User user);
}