package com.zz.ikeeping.pay.controller;

import com.zz.ikeeping.common.util.Base64Util;
import com.zz.ikeeping.pay.util.QrCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author DJL
 * @create 2019-06-28 16:52
 * @desc 根据接收的支付地址转为支付二维码
 **/
@Api(value = "支付链接生成二维码", tags = "支付链接生成二维码图片数据接口")
@Controller
public class QrCodeController {


    @ApiOperation("普通支付链接生成二维码图片")
    @GetMapping("/pay/qrcode/createqrcode.do")
    public void createQrCode(String msg, HttpServletResponse response) {
        // 根据支付地址生成二维码
        BufferedImage image = QrCodeUtil.createQrCode(msg, 400);
        // 将二维码图片通过响应对象输出到用户浏览器
        try {
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("Base64编码的支付链接生成二维码图片")
    @GetMapping("/pay/qrcode/payqrcode.do")
    public void payCode(String msg, HttpServletResponse response) {
        // 地址解码
        String payUrl = new String(Base64Util.base64Dec(msg));
        // 根据支付地址生成二维码
        BufferedImage image = QrCodeUtil.createQrCode(payUrl, 400);
        // 将二维码图片通过响应对象输出到用户浏览器
        try {
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
