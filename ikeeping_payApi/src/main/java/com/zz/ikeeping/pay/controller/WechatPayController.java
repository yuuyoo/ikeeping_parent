package com.zz.ikeeping.pay.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.pay.entity.WechatPay;
import com.zz.ikeeping.pay.service.WechatPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DJL
 * @create 2019-06-27 22:30
 * @desc 微信支付控制器
 **/
@Api(value = "统一的微信字符接口平台", tags = "微信支付数据接口")
@RestController
public class WechatPayController {

    @Autowired(required = false)
    private WechatPayService wechatPayService;

    // 生成订单支付信息
    @ApiOperation("生成微信支付订单信息")
    @PostMapping("/pay/wechat/orderpay.do")
    public R orderPay(@RequestBody WechatPay pay) {
        return wechatPayService.orderPay(pay);
    }

    // 查询订单支付状态
    @ApiOperation("查询微信支付订单信息")
    @GetMapping("/pay/wechat/querypay.do")
    public R queryOrderPay( String orderNo) {
        return wechatPayService.queryOrderPay(orderNo);
    }

    // 关闭支付订单
    @ApiOperation("关闭微信支付订单")
    @PostMapping("/pay/wechat/closepay.do")
    public R closeOrderPay(String orderNo) {
        return wechatPayService.closeOrderPay(orderNo);
    }
}
