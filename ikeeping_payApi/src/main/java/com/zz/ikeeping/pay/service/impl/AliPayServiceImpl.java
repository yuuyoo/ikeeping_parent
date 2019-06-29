package com.zz.ikeeping.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.zz.ikeeping.common.util.Base64Util;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.pay.config.SystemConfig;
import com.zz.ikeeping.pay.entity.AliPay;
import com.zz.ikeeping.pay.service.AliPayService;
import com.zz.ikeeping.pay.util.AliPayUtil;
import org.springframework.stereotype.Service;

/**
 * @author DJL
 * @create 2019-06-28 19:56
 * @desc 支付宝支付订单生成接口的实现类
 **/
@Service
public class AliPayServiceImpl implements AliPayService {

    // 生成订单支付信息
    public R orderPay(AliPay pay) {

        if (pay != null) {
            // 设置支付金额为0.01元 支付宝默认支付单位为元
            pay.setTotal_amount(0.01);
            try {
                String payUrl = AliPayUtil.aliPay(pay);
                if (payUrl != null && payUrl.length() > 0) {
                    String m = Base64Util.base64Enc(payUrl.getBytes());
                    return R.setOK("支付宝支付链接成功生成", SystemConfig.PAYURL + "pay/qrcode/payqrcode.do?msg=" + m);
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }

        }

        return R.setERROR("网络异常，请联系客服");
    }

    // 查询订单支付状态
    public R queryOrderPay(String orderNo) {

        if (orderNo != null && orderNo.length() > 0) {
            try {
                String msg = AliPayUtil.aliPayQuery(orderNo);
                if (msg != null && msg.length() > 0) {
                    return R.setOK("支付订单查询成功", msg);
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }

        }

        return R.setERROR("网络异常，请稍后再试");
    }

    // 关闭支付订单
    public R closeOrderPay(String orderNo) {
        if (orderNo != null && orderNo.length() > 0) {
            try {
                String msg = AliPayUtil.closeOrder(orderNo);
                if (msg != null && msg.length() > 0) {
                    return R.setOK(msg);
                }
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }

        }

        return R.setERROR("网络异常，请稍后再试");

    }
}
