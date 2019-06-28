package com.zz.ikeeping.pay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author DJL
 * @create 2019-06-27 21:03
 * @desc http客户端工具类
 **/
public class HttpClientUtil {

    // 超时时间 毫秒数
    private static final int CONNECT_TIMEOUT = 5000;
    // 字符编码 utf-8
    private final static String DEFAULT_ENCODING = "UTF-8";

    /**
     * 发送http请求不指定文档类型
     * @param urlStr url地址
     * @param data 发送的数据
     * @return
     */
    public static String postData(String urlStr, String data){
        return postData(urlStr, data, null);
    }

    /**
     * 发送指定文档类型的http请求
     * @param urlStr url地址
     * @param data 发送的数据
     * @param contentType 文档类型
     * @return
     */
    public static String postData(String urlStr, String data, String contentType){
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if(contentType != null){
                conn.setRequestProperty("content-type",contentType);
            }
            OutputStreamWriter writer = new
                    OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if(data == null) {
                data = "";
            }
            writer.write(data);
            writer.flush();
            writer.close();
            reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            System.err.println("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }
}
