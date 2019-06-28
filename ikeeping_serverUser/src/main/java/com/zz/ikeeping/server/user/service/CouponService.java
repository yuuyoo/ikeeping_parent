package com.zz.ikeeping.server.user.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;

public interface CouponService {
    R save(Coupon coupon);
    R findAll();
}
