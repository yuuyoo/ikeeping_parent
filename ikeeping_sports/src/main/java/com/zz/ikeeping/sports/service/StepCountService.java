package com.zz.ikeeping.sports.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.StepCount;

public interface StepCountService {
    R insert(StepCount stepCount);
    R findByUid(int uid);
}
