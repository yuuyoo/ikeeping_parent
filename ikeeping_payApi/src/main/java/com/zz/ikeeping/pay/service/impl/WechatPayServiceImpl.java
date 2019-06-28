package com.zz.ikeeping.pay.service.impl;

import com.zz.ikeeping.common.util.Base64Util;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.pay.config.SystemConfig;
import com.zz.ikeeping.pay.entity.WechatPay;
import com.zz.ikeeping.pay.service.WechatPayService;
import com.zz.ikeeping.pay.util.CommonPayUtil;
import com.zz.ikeeping.pay.util.XMLUtil;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author DJL
 * @create 2019-06-27 22:10
 * @desc ${DESCRIPTION}
 **/
@Service
public class WechatPayServiceImpl implements WechatPayService {
    @Override
    public R orderPay(WechatPay pay) {

        try {
            // 微信支付地址
            String url = CommonPayUtil.wechatPay(pay.getPrice() + "", pay.getOrderInfo(),
                    pay.getOrderNo());
            // 支付地址进行base64编码，防止浏览器地址错误
            if (url != null && url.length() > 0) {
                String m = Base64Util.base64Enc(url.getBytes());
                String payUrl = SystemConfig.PAYURL + "pay/qrcode/payqrcode.do?msg=" + m;
                return R.setOK("支付二维码生成", payUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return R.setERROR("网络异常，暂无法支付，请联系客服");
    }

    @Override
    public R queryOrderPay(String orderNo) {
        String xml = CommonPayUtil.wechatPayQuery(orderNo);
        try {
            Map<String, Object> map = XMLUtil.doXMLParse(xml);
            if (map.containsKey("trade_state")) {
                String state = map.get("trade_state").toString();
                return R.setOK("支付账单查询成功", state);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.setERROR("网络异常，暂无法操作，请稍后再试");
    }

    @Override
    public R closeOrderPay(String orderNo) {
        String xml = CommonPayUtil.closeOrder(orderNo);
        try {
            Map<String, Object> map = XMLUtil.doXMLParse(xml);
            if (map.containsKey("result_code")) {
                String state = map.get("result_code").toString();
                return R.setOK("取消订单成功", state);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.setERROR("网络异常，暂无法操作，请稍后再试");
    }
}
