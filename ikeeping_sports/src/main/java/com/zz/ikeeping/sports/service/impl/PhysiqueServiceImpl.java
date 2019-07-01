package com.zz.ikeeping.sports.service.impl;

import com.alibaba.druid.sql.dialect.h2.visitor.H2ASTVisitor;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.BMI;
import com.zz.ikeeping.sports.config.BMIType;
import com.zz.ikeeping.sports.dao.BMIMapper;
import com.zz.ikeeping.sports.service.PhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysiqueServiceImpl implements PhysiqueService {
    @Autowired
    private BMIMapper mapper;
    @Override
    public String calBMI(BMI bmi) {
       Float weight = bmi.getWeight();
       float height = bmi.getHight();
        height = height / 100;
        float b = weight / (height * height);
        String t = null;
        if (b < 24) {
            bmi.setStatus(1);
            t = BMIType.health.getValue();
        } else {
            bmi.setStatus(2);
           t = BMIType.hubHeath.getValue();
        }
        mapper.insert(bmi);
        return t;
    }

    @Override
    public R findByUid(int uid) {
        return R.setOK("OK",mapper.findByUid(uid));
    }
}
