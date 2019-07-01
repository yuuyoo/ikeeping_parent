package com.zz.ikeeping.sports.service.impl;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.StepCount;
import com.zz.ikeeping.sports.dao.StepCountMapper;
import com.zz.ikeeping.sports.service.StepCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepCountServiceImpl implements StepCountService {
    @Autowired
    private StepCountMapper mapper;
    @Override
    public R insert(StepCount stepCount) {

        return R.setOK("OK",mapper.insert(stepCount));
    }

    @Override
    public R findByUid(int uid) {
        return R.setOK("OK",mapper.findByUid(uid));
    }
}
