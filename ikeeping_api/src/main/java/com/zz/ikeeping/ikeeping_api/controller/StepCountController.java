package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.StepCount;
import com.zz.ikeeping.ikeeping_api.service.StepCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "运动计步接口",tags = "运动计步接口")
public class StepCountController {
    @Autowired
    private StepCountService service;
    //添加用户的步数
    @ApiOperation(value ="添加用户的步数" )
    @PostMapping("api/step/insert.do")
    public R insertStep(@RequestBody StepCount stepCount){
        return service.insert(stepCount);
    }
   //根据用户uid查询用户的步数
    @ApiOperation(value = "根据用户uid查询用户的步数")
    @GetMapping("api/step/find.do")
    public R findStep(@RequestParam("uid") int uid){
        return service.findByUid(uid);
    }
}
