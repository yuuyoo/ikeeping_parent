package com.zz.ikeeping.sports.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.StepCount;
import com.zz.ikeeping.sports.service.StepCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StepCountController {
    @Autowired
    private StepCountService service;

    /**
     * 添加用户的步数
     * @param stepCount
     * @return
     */
    @PostMapping("step/insert.do")
    public R insetStep(@RequestBody StepCount stepCount){
        System.out.println(stepCount.getStepcount());
        System.out.println(stepCount.getUid());
        return service.insert(stepCount);
    }

    /**
     * 根据用户uid查询用户的步数
     * @param uid
     * @return
     */
    @GetMapping("step/find.do")
    public R findStep(int uid){
        return service.findByUid(uid);
    }
}
