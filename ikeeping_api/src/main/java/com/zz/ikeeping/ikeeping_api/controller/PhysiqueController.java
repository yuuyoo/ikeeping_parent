package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.BMI;
import com.zz.ikeeping.ikeeping_api.service.PhysiqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "健康指数接口",tags = "健康指数接口")
public class PhysiqueController {
    @Autowired
    private PhysiqueService service;

    //通过给出体重和身高计算身体质量指数，返回结果
    @ApiOperation(value = "计算健康指数接口")
    @PostMapping("api/sports/physique.do")
    public String calBMI(@RequestBody BMI bmi){
        return service.calBMI(bmi);
    }
    //根据用户uid查询健康指数
    @ApiOperation(value = "查询健康指数接口")
    @GetMapping("api/sports/list.do")
    public R findByUid(@RequestParam("uid") int uid) {
      return service.findByUid(uid);
    }
}
