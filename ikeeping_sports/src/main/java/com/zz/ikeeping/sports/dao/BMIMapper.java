package com.zz.ikeeping.sports.dao;

import com.zz.ikeeping.entity.BMI;

public interface BMIMapper {
    int insert (BMI bmi);
    BMI findByUid(int uid);
}
