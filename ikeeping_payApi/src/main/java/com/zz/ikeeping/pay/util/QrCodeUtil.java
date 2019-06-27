package com.zz.ikeeping.pay.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DJL
 * @create 2019-06-27 17:50
 * @desc 支付二维码生成工具类
 **/
public class QrCodeUtil {

    /**
     * 生成二维码图片
     * @param content 二维码中存储的内容
     * @param width 二维码的宽度（正方形只要一个边长就可以了）
     * @return
     */
    public static BufferedImage createQrCode(String content, int width) {
        // 配置属性
        Map<EncodeHintType, Object> map = new HashMap<>();
        // 图片质量
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 外边距
        map.put(EncodeHintType.MARGIN, 1);
        // 字符集
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            // 生成矩阵 参数说明：1.二维码的内容，2.矩阵的类型（条形码，二维码）3.宽度，4.高度，5.属性设置map集合
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, width, map);
            // 设置颜色
            MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
            // 生成图片
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
            return image;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 将生成的二维码图片保存至文件中
     * @param content 二维码保存的内容
     * @param width 宽
     * @param file 需要保存的文件对象
     * @return
     */
    public static String writeToFile(String content, int width, File file) {
        if (null == file) {
            return null;
        }

        BufferedImage image = createQrCode(content, width);
        if (image != null) {
            try {
                ImageIO.write(image, "png", file);
                return file.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
