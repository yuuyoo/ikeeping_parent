package com.zz.ikeeping.ikeeping_api.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.BMI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PhysiqueService {
    /**
            * 通过给出体重和身高计算身体质量指数，返回结果
     */
    @PostMapping("sports/physique.do")
    public String calBMI(@RequestBody BMI bmi);
    /**
     * 根据用户uid查询健康指数
     * @param uid
     * @return
     */
    @GetMapping("sports/single.do")
    public R findByUid(@RequestParam("uid") int uid);
}
