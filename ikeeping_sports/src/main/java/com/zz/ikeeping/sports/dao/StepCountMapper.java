package com.zz.ikeeping.sports.dao;

import com.zz.ikeeping.entity.StepCount;

import java.util.List;

public interface StepCountMapper {
    int insert (StepCount stepCount);
    StepCount findByUid(int uid);
}
