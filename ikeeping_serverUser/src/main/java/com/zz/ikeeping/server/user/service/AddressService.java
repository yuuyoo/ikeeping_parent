package com.zz.ikeeping.server.user.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Address;
import com.zz.ikeeping.entity.Coupon;

public interface AddressService {
    // 查询指定用户的所有非默认的收货地址
    R selectById(String token);
    // 查询指定用户的默认收货地址
    R selectByDefult(String token);
    // 删除指定的收货地址
    R deleteById(Integer addid);
    // 将非默认的收货地址改为默认收货地址
   // R updateDefult(Integer addid);

    // 新增收货地址
    R insert(Address address);

    // 修改收货地址
    R updateByAddid(Address address);

    interface CouponService {
        R save(Coupon coupon);
        R findAll();
    }
}
