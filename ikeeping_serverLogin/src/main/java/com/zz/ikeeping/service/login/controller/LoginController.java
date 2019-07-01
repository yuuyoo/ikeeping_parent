package com.zz.ikeeping.service.login.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import com.zz.ikeeping.service.login.service.CartVoService;
import com.zz.ikeeping.service.login.service.LoginService;
import com.zz.ikeeping.service.login.service.OrdersStstusService;
import com.zz.ikeeping.service.login.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserUpdateService updateService;
    @Autowired
    private CartVoService cartVoService;
    @Autowired
    private OrdersStstusService ststusService;

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

    //根据id删除购物车的商品信息
    @GetMapping("/cart/delete.do")
    public R delete(@RequestParam(value="id",required=true) List<Integer> id){
        return  cartVoService.delete(id);
    }

    //查找购物车的所有的商品信息包括商品名称、价格、图片地址、数量
    @GetMapping("/cart/all.do")
    public R selectAllCart(){
        return cartVoService.all();
    }

    //根据状态查询订单
    @GetMapping("/order/select.do")
    public R selectByStatus(@RequestParam("status") int status, @RequestParam("uid") int uid){
        return ststusService.selectByStatus(status,uid);
    }
}
