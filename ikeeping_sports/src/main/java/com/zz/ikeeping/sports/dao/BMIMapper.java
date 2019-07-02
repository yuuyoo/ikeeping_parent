package com.zz.ikeeping.sports.dao;

import com.zz.ikeeping.entity.BMI;

import java.util.List;

public interface BMIMapper {
    int insert (BMI bmi);
    List<BMI> findByUid(int uid);
}
