package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.ikeeping_api.service.UserSignService;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserSignController {
    @AutoConfigureOrder
    private UserSignService signService;
    @GetMapping("usersign/all.do")
    public R all(HttpServletRequest request){
        return signService.all(request.getHeader(ProjectConfig.TOKENHEAD));
    }
    @GetMapping("/usersign/single.do")
    public R single(HttpServletRequest request){
        return signService.single(request.getHeader(ProjectConfig.TOKENHEAD));
    }
    @GetMapping("/usersign/")
    public R signDays(HttpServletRequest request,@RequestParam("days") int days){
        return signService.signDays(request.getHeader(ProjectConfig.TOKENHEAD),days);
    }
    @PostMapping("/usersign/signsave.do")
    public  R save(HttpServletRequest request){
        return signService.save(request.getHeader(ProjectConfig.TOKENHEAD));
    }
}
