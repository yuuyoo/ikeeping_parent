package com.zz.ikeeping.pay.util;


import com.zz.ikeeping.pay.config.WechatPayConfig;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author DJL
 * @create 2019-06-27 20:24
 * @desc 支付通用工具类
 **/
public class CommonPayUtil {


    /**
     * 验证微信支付签名是否一致
     * @param characterEncoding 字符编码
     * @param packageParams 参数字段map集合
     * @param apiKey 商家的API_KEY 签名秘钥
     * @return
     */
    public static boolean isWechatPaySign(String characterEncoding,
                                          SortedMap<Object, Object> packageParams, String apiKey) {
        StringBuffer sb=new StringBuffer();
        Set es= packageParams.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + apiKey);
        String mysign =MD5Util.MD5Encode(sb.toString(),characterEncoding).toLowerCase();
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();
        return tenpaySign.equals(mysign);
    }

    /**
     * 生成微信支付签名
     * @param characterEncoding 字符编码
     * @param packageParams 参数map集合 SortedMap自然排序
     * @param API_KEY 商家的API_KEY 签名秘钥
     * @return
     */
    public static String createSign(String characterEncoding,
                                    SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) &&
                    !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        String sign =MD5Util.MD5Encode(sb.toString(),characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * 获取发送请求的xml
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
            ) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            } }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 获取指定大小的正整数
     * @param length 正整数的长度
     * @return
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取当前系统时间
     * @return
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 调用预支付接口 获取支付链接
     * @param order_price 价格 单位 分
     * @param body 商品描述
     * @param out_trade_no 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一*/
    public static String wechatPay(String order_price, String body, String out_trade_no) throws Exception {
        String ip= Inet4Address.getLocalHost().getHostAddress();
        String appid = WechatPayConfig.APP_ID;
        String key = WechatPayConfig.API_KEY;
        String currTime = getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        /*
        JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
        MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
         */
        String trade_type = "NATIVE";
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", WechatPayConfig.MCH_ID);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        // packageParams.put("total_fee", order_price);
        // 支付金额 单位为分
        packageParams.put("total_fee", "1");
        packageParams.put("spbill_create_ip", ip);
        packageParams.put("notify_url", "http://"+ip+":8080/paycallback");
        packageParams.put("trade_type", trade_type);
        String sign = createSign("UTF-8", packageParams, key);
        packageParams.put("sign", sign);
        String requestXML = getRequestXml(packageParams);
        System.out.println("请求----->"+requestXML);
        String resXml = HttpClientUtil.postData(WechatPayConfig.UFDOOER_URL, requestXML);
        System.out.println("结果----->"+resXml);
        Map map = XMLUtil.doXMLParse(resXml);
        String urlCode = (String) map.get("code_url");
        return urlCode;
    }

    /**
     * 支付结果查询
     * @param orderno 订单号
     * @return
     */
    public static String wechatPayQuery(String orderno){
        String currTime = getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", WechatPayConfig.APP_ID);
        packageParams.put("mch_id", WechatPayConfig.MCH_ID);
        packageParams.put("out_trade_no", orderno);
        packageParams.put("nonce_str", nonce_str);
        String sign = createSign("UTF-8", packageParams, WechatPayConfig.API_KEY);
        packageParams.put("sign", sign);
        String requestXML = getRequestXml(packageParams);
        System.out.println("查询请求----->"+requestXML);
        String resXml = HttpClientUtil.postData(WechatPayConfig.QUERY_URL, requestXML);
        System.out.println("查询结果----->"+resXml);
        return resXml;
    }

    /**
     * 取消支付订单
     * @param orderNo 订单号
     * @return
     */
    public static String closeOrder(String orderNo){
        String currTime = getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", WechatPayConfig.APP_ID);
        packageParams.put("mch_id", WechatPayConfig.MCH_ID);
        packageParams.put("out_trade_no", orderNo);
        packageParams.put("nonce_str", nonce_str);
        String sign = createSign("UTF-8", packageParams, WechatPayConfig.API_KEY);
        packageParams.put("sign", sign);
        String requestXML = getRequestXml(packageParams);
        System.out.println("查询请求----->"+requestXML);
        String resXml = HttpClientUtil.postData(WechatPayConfig.CLOSE_URL, requestXML);
        System.out.println("查询结果----->"+resXml);
        return resXml;
    }

    /**
     * 查询退款订单的信息
     * @param orderNo 订单编号
     * @return
     */
    public static String queryRefundOrder(String orderNo){
        String currTime = getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", WechatPayConfig.APP_ID);
        packageParams.put("mch_id", WechatPayConfig.MCH_ID);
        packageParams.put("out_trade_no", orderNo);
        packageParams.put("nonce_str", nonce_str);
        String sign = createSign("UTF-8", packageParams, WechatPayConfig.API_KEY);
        packageParams.put("sign", sign);
        String requestXML = getRequestXml(packageParams);
        System.out.println("查询请求----->"+requestXML);
        String resXml = HttpClientUtil.postData(WechatPayConfig.REFUND_URL, requestXML);
        System.out.println("查询结果----->"+resXml);
        return resXml;
    }
}
