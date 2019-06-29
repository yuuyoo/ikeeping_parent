package com.zz.ikeeping.pay.util;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.zz.ikeeping.pay.config.AliPayConfig;
import com.zz.ikeeping.pay.entity.AliPay;

/**
 * @author DJL
 * @create 2019-06-28 19:43
 * @desc 支付宝支付生成工具类
 **/
public class AliPayUtil {
    // 支付客户端对象
    private static AlipayClient alipayClient;

    // 初始化aliapyClient
    static {
        alipayClient = new DefaultAlipayClient(AliPayConfig.PAY_URL, AliPayConfig.APP_ID,
                AliPayConfig.APP_PRIVATE_KEY, "json", "UTF-8", AliPayConfig.ALIPAY_PUBLIC_KEY,
                "RSA2");
    }

    /**
     * 生成预支付链接  将信息写入到二维码
     * @param pay 订单信息实体类
     * @return
     * @throws AlipayApiException
     */
    public static String aliPay(AliPay pay) throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(JSON.toJSONString(pay));
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            return response.getQrCode();
        } else {
            return null;
        }
    }

    /**
     * 查询订单的交易状态 支付完查询显示支付完成
     * @param orderNo 订单编号
     * @return
     * @throws AlipayApiException
     */
    public static String aliPayQuery(String orderNo) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":"+orderNo+" }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            return response.getTradeStatus();
        } else {
            return null;
        }
    }

    /**
     * 取消支付订单 注：暂时有问题
     * @param orderNo 订单编号
     * @return
     * @throws AlipayApiException
     */
    public static String closeOrder(String orderNo) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{\"out_trade_no\":"+orderNo+" }");
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            return response.getSubCode();
        } else {
            return null;
        }
    }
}
