package com.zz.ikeeping.server.user.dao;
import com.zz.ikeeping.entity.Coupon;

import java.util.List;

public interface CouponMapper {
    int insert(Coupon record);
    List<Coupon> findAll();
}
