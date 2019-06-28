package com.zz.ikeeping.userserver.coupon.service.impl;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;
import com.zz.ikeeping.userserver.coupon.dao.CouponMapper;
import com.zz.ikeeping.userserver.coupon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public R save(Coupon coupon) {
        return R.setOK("OK",couponMapper.insert(coupon));
    }

    @Override
    public R findAll() {
        return R.setOK("OK",couponMapper.findAll());
    }
}
