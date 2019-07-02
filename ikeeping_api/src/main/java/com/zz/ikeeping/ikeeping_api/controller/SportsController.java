package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.ikeeping_api.service.SportsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "身体素质接口",tags = "身体素质接口")
public class SportsController {
    @Autowired
    private SportsService service;
    //根据用户输入的身高和体重计算用户的身体素质
    @ApiOperation(value = "计算用户的身体素质")
    @GetMapping("api/sports/calculate.do")
    public String calBMI(@RequestParam("weight") float weight, @RequestParam("height") float height){
        return service.calBMI(height, weight);
    }
}
