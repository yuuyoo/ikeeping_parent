package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.StepCount;
import com.zz.ikeeping.ikeeping_api.service.StepCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R insertStep(@RequestBody StepCount stepCount){
        return service.insert(stepCount);
    }
    /**
     * 根据用户uid查询用户的步数
     * @param uid
     * @return
     */
    @GetMapping("step/find.do")
    public R findStep(@RequestParam("uid") int uid){
        return service.findByUid(uid);
    }
}
