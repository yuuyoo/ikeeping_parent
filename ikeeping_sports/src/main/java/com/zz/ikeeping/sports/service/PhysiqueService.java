package com.zz.ikeeping.sports.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.BMI;

public interface PhysiqueService {
    String calBMI(BMI bmi);
    R findByUid(int uid);
}
