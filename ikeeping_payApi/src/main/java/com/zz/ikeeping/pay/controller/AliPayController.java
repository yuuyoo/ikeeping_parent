package com.zz.ikeeping.pay.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.pay.entity.AliPay;
import com.zz.ikeeping.pay.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DJL
 * @create 2019-06-28 20:11
 * @desc 支付宝支付控制器端口
 **/
@Api(value = "统一的支付宝支付接口平台", tags = "支付宝支付数据接口")
@RestController
public class AliPayController {

    @Autowired(required = false)
    private AliPayService aliPayService;

    // 生成订单支付信息
    @ApiOperation("生成支付宝支付订单信息")
    @PostMapping("/pay/alipay/orderpay.do")
    public R orderPay(@RequestBody AliPay pay) {
        return aliPayService.orderPay(pay);
    }

    // 查询订单支付状态
    @ApiOperation("查询支付宝支付订单信息")
    @GetMapping("/pay/alipay/querypay.do")
    public R queryOrderPay( String orderNo) {
        return aliPayService.queryOrderPay(orderNo);
    }

    // 关闭支付订单
    @ApiOperation("关闭支付宝支付订单")
    @PostMapping("/pay/alipay/closepay.do")
    public R closeOrderPay(String orderNo) {
        return aliPayService.closeOrderPay(orderNo);
    }
}
