package com.zz.ikeeping.server.user.service.impl;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;
import com.zz.ikeeping.server.user.dao.CouponMapper;
import com.zz.ikeeping.server.user.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceimpl implements CouponService {
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
