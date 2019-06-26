package com.zz.ikeeping.common.util;

import java.util.Base64;

public class Base64Util {
    //转换为Base64
    public static String base64Enc(byte[] msg) {
        return Base64.getEncoder().encodeToString(msg);
    }
    //解码
    public static byte[] base64Dec(String msg) {
        return Base64.getDecoder().decode(msg);
    }
}