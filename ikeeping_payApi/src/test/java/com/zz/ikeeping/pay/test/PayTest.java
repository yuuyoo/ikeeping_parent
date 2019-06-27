package com.zz.ikeeping.pay.test;

import com.zz.ikeeping.pay.util.CommonPayUtil;
import com.zz.ikeeping.pay.util.QrCodeUtil;
import org.junit.Test;

import java.io.File;

/**
 * @author DJL
 * @create 2019-06-27 19:58
 * @desc 支付接口测试类
 **/
public class PayTest {

    @Test
    public void qrCodeTest() {
        File file = new File("hello.png");
        System.out.println(QrCodeUtil.writeToFile("DJL 你好！", 300, file));
    }

    @Test
    public void wechatPayTest() {
        String url= null;
        try {
            url = CommonPayUtil.wechatPay("1","测试微信支付","198908080001");
            System.out.println("支付链接："+url);
            File file = new File("pay.png");
            System.out.println(QrCodeUtil.writeToFile(url, 300, file));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wechatPayQueryTest() {
        System.out.println(CommonPayUtil.wechatPayQuery("198908080001"));
    }
}
