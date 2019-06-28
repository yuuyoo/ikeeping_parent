package com.zz.ikeeping.pay.entity;

import lombok.Data;

/**
 * @author DJL
 * @create 2019-06-28 19:51
 * @desc ${DESCRIPTION}
 **/
@Data
public class AliPay {
    //订单编号
    private String out_trade_no;
    //价格
    private double total_amount;
    //商品信息
    private String subject;
}
