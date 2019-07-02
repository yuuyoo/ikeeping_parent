package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.ikeeping_api.service.UserSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "用户签到接口",tags = "用户签到接口")
public class UserSignController {
    @Autowired
    private UserSignService signService;
    //查询所有的签到信息
    @ApiOperation(value = "查询所有的签到信息")
    @GetMapping("usersign/all.do")
    public R all(HttpServletRequest request){
        return signService.all(request.getHeader(ProjectConfig.TOKENHEAD));
    }

    @ApiOperation(value = "查看最近签到信息")
    @GetMapping("/usersign/single.do")
    public R single(HttpServletRequest request){
        return signService.single(request.getHeader(ProjectConfig.TOKENHEAD));
    }
    @ApiOperation(value = "5/30天内签到信息")
    @GetMapping("/usersign/day.do")
    public R signDays(HttpServletRequest request,@RequestParam("days") int days){
        return signService.signDays(request.getHeader(ProjectConfig.TOKENHEAD),days);
    }
    @ApiOperation(value = "签到")
    @PostMapping("/usersign/signsave.do")
    public  R save(HttpServletRequest request){
        return signService.save(request.getHeader(ProjectConfig.TOKENHEAD));
    }
}
