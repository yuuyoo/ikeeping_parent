package com.zz.ikeeping.pay.service;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.pay.entity.WechatPay;

/**
 * @author DJL
 * @create 2019-06-27 22:05
 * @desc ${DESCRIPTION}
 **/
public interface WechatPayService {

    // 生成订单支付信息
    R orderPay(WechatPay pay);

    // 查询订单支付状态
    R queryOrderPay(String orderNo);

    // 关闭支付订单
    R closeOrderPay(String orderNo);
}
