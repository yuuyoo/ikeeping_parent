package com.zz.ikeeping.server.user.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.server.user.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserSignController {
    @Autowired
    private UserSignService signService;

    //查询所有签到记录
    @GetMapping("usersign/all.do")
    public R all(@RequestParam("token") String token){
        return signService.queryByUid(token);
}
    //查询最近的签到信息
    @GetMapping("/usersign/single.do")
    public R single(@RequestParam("token") String token){
        return signService.querySingle(token);
    }
    //查询5/30天内签到
    @GetMapping("/usersign/day.do")
    public R signDays(@RequestParam("token") String token,@RequestParam("days") int days){
        return signService.queryByDays(token, days);
    }
    //签到
    @PostMapping("/usersign/signsave.do")
    public  R save(@RequestParam("token") String token){
        return signService.saveSign(token);
    }

}
