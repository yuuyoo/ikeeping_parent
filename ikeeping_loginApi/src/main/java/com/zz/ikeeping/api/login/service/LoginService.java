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
import java.util.List;

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
    //更新用户信息
    @PostMapping("/user/update.do")
    R update(@RequestBody User user);

    //根据id删除购物车的信息
    @GetMapping("/cart/delete.do")
    R delete(@RequestParam(value="id",required=true) List<Integer> id);

    //查询购物车的所有商品的信息，展示商品的名称、价格、图片地址、数量
    @GetMapping("/cart/all.do")
    R selectAllCart();

    //根据状态查询订单详情
    @GetMapping("/order/.select.do")
    R selectByStatus(@RequestParam("status") int status,@RequestParam("uid") int uid);
}