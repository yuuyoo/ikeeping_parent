package com.zz.ikeeping.userserver.coupon.dao;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    int insert(Coupon record);
    List<Coupon> findAll();
}
