package com.zz.ikeeping.sports.service.impl;

import com.zz.ikeeping.sports.config.BMIType;
import com.zz.ikeeping.sports.dao.BMIMapper;
import com.zz.ikeeping.sports.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportsServiceImpl implements SportsService {
    @Autowired
    private BMIMapper mapper;

    @Override
    public String calBMI(float weight, float height) {
        height = height / 100;
        float bmi = weight / (height * height);

        if (bmi < 18.5){
            return (BMIType.slim.getValue());
        } else if(bmi >= 18.5 && bmi < 24) {
            return (BMIType.normal.getValue());
        }else if(bmi >= 24 && bmi < 28) {
            return (BMIType.overweight.getValue());
        } else {
            return (BMIType.obesity.getValue());
        }
    }
}
