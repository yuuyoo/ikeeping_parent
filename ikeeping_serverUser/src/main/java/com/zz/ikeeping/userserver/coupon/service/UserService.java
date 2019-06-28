package com.zz.ikeeping.userserver.coupon.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;

public interface UserService {
    R save(Coupon coupon);
    R findAll();
}
