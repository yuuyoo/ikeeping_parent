package com.zz.ikeeping.ikeeping_api.coupon.controller;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;
import com.zz.ikeeping.ikeeping_api.coupon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("user/coupon/save.do")
    public R save(@RequestBody Coupon coupon){
        return userService.save(coupon);
    }
    @GetMapping("user/coupon/all.do")
    public R findAll(){
        return userService.findAll();
    }
}
