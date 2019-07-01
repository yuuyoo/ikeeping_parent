package com.zz.ikeeping.pay.config;

/**
 * @author DJL
 * @create 2019-06-27 17:37
 * @desc 微信支付配置类
 **/
public class WechatPayConfig {

    // 微信支付应用ID
    public static final String APP_ID = "wx632c8f211f8122c6";
    // 微信支付商家ID
    public static final String MCH_ID = "1497984412";
    // 微信支付秘钥
    public static final String API_KEY = "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    // 下单接口地址
    public static final String UFDOOER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 支付查询接口
    public static final String QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 支付订单取消地址
    public static final String CLOSE_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
    // 支付退款地址
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    // 支付回调通知地址
    public static final String NOTIFY_URL = "http://114.242.26.51/pay/result/notice.do";


}
