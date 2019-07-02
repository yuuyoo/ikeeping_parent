package com.zz.ikeeping.ikeeping_api.controller;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Coupon;
import com.zz.ikeeping.ikeeping_api.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "优惠券接口",tags = "优惠券接口")
public class CouponController {
    @Autowired
    private CouponService userService;

    //增加优惠券
    @ApiOperation("添加优惠券接口")
    @PostMapping("api/user/coupon/save.do")
    public R save(@RequestBody Coupon coupon){
        return userService.save(coupon);
    }

    //查询所有的优惠券
    @ApiOperation("查询所有的优惠券接口")
    @GetMapping("api/user/coupon/all.do")
    public R findAll(){
        return userService.findAll();
    }
}
