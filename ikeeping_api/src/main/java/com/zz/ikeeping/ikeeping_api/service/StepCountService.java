package com.zz.ikeeping.ikeeping_api.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.StepCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SportsProvider")
public interface StepCountService {
    /**
     * 添加用户的步数
     * @param stepCount
     * @return
     */
    @PostMapping("step/insert.do")
    public R insert(@RequestBody StepCount stepCount);

    /**
     * 根据用户uid查询用户的步数
     * @param uid
     * @return
     */
    @GetMapping("step/find.do")
    public R findByUid(@RequestParam("uid") int uid);
}
