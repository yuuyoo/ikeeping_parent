package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.BMI;
import com.zz.ikeeping.ikeeping_api.service.PhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
public class PhysiqueController {
    @Autowired
    private PhysiqueService service;

    /**
     * 通过给出体重和身高计算身体质量指数，返回结果
     */
    @PostMapping("sports/physique.do")
    public String calBMI(@RequestBody BMI bmi){
        return service.calBMI(bmi);
    }

    /**
     * 根据用户uid查询健康指数
     * @param uid
     * @return
     */
    @GetMapping("sports/single.do")
    public R findByUid(@RequestParam("uid") int uid) {
      return service.findByUid(uid);
    }
}
