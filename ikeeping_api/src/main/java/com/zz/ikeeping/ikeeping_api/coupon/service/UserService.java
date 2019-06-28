package com.zz.ikeeping.ikeeping_api.coupon.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CouponProvider")
public interface UserService {
    @PostMapping("user/coupon/save.do")
    R save(@RequestBody Coupon coupon);
    @GetMapping("user/coupon/all.do")
    R findAll();
}
