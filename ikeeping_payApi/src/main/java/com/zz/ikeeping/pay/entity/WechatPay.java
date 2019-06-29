package com.zz.ikeeping.pay.entity;

import lombok.Data;

/**
 * @author DJL
 * @create 2019-06-27 22:03
 * @desc ${DESCRIPTION}
 **/
@Data
public class WechatPay {
    // 订单号
    private String orderNo;
    // 订单信息
    private String orderInfo;
    // 订单交易金额
    private int price;
}
