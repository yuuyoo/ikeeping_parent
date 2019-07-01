package com.zz.ikeeping.api.login.controller;

import com.zz.ikeeping.api.login.service.LoginService;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.vo.R;

import com.zz.ikeeping.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
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

    //根据id删除购物车的商品信息
    @GetMapping("/api/cart/delete.do")
    public R delete(@RequestParam(value="id",required=true) List<Integer> id){
        return loginService.delete(id);
    }

    //查询购物车的有所的商品信息展示购物车的商品的名称、价格、图片地址、数量
    @GetMapping("/api/cart/all.do")
    public R selectAllCart(){
        return loginService.selectAllCart();
    }

    //根据状态查询订单详情
    @GetMapping("/api/order/select.do")
    public R selectByStatus(int status, int uid){
        return loginService.selectByStatus(status,uid);
    }
}
