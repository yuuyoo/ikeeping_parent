package com.zz.ikeeping.ikeeping_api.controller;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;
import com.zz.ikeeping.ikeeping_api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CouponController {
    @Autowired
    private CouponService userService;

    /**
     * 增加优惠券
     * @param coupon 实体类对象
     * @return
     */
    @PostMapping("user/coupon/save.do")
    public R save(@RequestBody Coupon coupon){
        return userService.save(coupon);
    }

    /**
     * 查询所有的优惠券
     * @return
     */
    @GetMapping("user/coupon/all.do")
    public R findAll(){
        return userService.findAll();
    }
}
