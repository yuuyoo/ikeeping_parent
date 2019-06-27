package com.zz.ikeeping.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现主流的加解密算法
 */
public class EncryptionUtil {
    public static final String SHA1="SHA-1";
    public static final String SHA256="SHA-256";
    public static final String SHA512="SHA-512";
    public static final String AES="AES";
    public static final String RSA="RSA";
    public static final String PUBLICKEY="pubkey";
    public static final String PRIVATEKEY="prikey";

    //base64
    public static String md5Enc(String msg)  {
        try {
            //创建MD5摘要解析器
            MessageDigest messageDigest=MessageDigest.getInstance("MD5");
            //进行加密
            messageDigest.update(msg.getBytes());
            //获取加密结果
            byte[] arr=messageDigest.digest();
            return Base64Util.base64Enc(arr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //MD5安全加密 1、要加密的内容 2、盐 干扰内容 3、加密次数
    public static String md5Enc(String msg,String slat,int count)  {
        String s=msg+slat;
        for(int i=1;i<=count;i++){
            s= md5Enc(s);
        }
        return s;
    }
    //进行SHA安全散列加密 结果位base64
    public static String SHAEnc(String type,String msg){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance(type);
            messageDigest.update(msg.getBytes());
            return Base64Util.base64Enc(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //对称加密
    //生成对称加密的秘钥
    public static String createAESKEY(){
        try {
            //创建秘钥生成器对象
            KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
            SecretKey secretKey=keyGenerator.generateKey();
            return Base64Util.base64Enc(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //加密
    public static String AESEnc(String key,String msg){
        //将字符串的秘钥转换为秘钥对象
        SecretKeySpec keySpec=new SecretKeySpec(Base64Util.base64Dec(key),"AES");
        try {
            //创建加密器
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,keySpec);
            return Base64Util.base64Enc(cipher.doFinal(msg.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //解密
    public static String AESDec(String key,String msg){
        //将字符串的秘钥转换为秘钥对象
        SecretKeySpec keySpec=new SecretKeySpec(Base64Util.base64Dec(key),"AES");
        try {
            //创建加密器
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,keySpec);
            return new String(cipher.doFinal(Base64Util.base64Dec(msg)),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //创建秘钥对儿
    public static Map<String,String> createRSAKey(){
        try {
            //创建生成器
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
            //初始化秘钥长度 2的n次幂
            keyPairGenerator.initialize(512);
            KeyPair keyPair=keyPairGenerator.generateKeyPair();
            Map<String,String> map=new HashMap<>();
            map.put(PUBLICKEY,Base64Util.base64Enc(keyPair.getPublic().getEncoded()));
            map.put(PRIVATEKEY,Base64Util.base64Enc(keyPair.getPrivate().getEncoded()));
            return map;


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //私钥--加密
    public static String RSAEnc(String key,String msg){
        try {
            //转换私钥
            PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(Base64Util.base64Dec(key));
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PrivateKey privateKey=keyFactory.generatePrivate(keySpec);
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,privateKey);
            return Base64Util.base64Enc(cipher.doFinal(msg.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //公钥--解密
    public static String RSADec(String key,String msg){
        try {
            //转换公钥
            X509EncodedKeySpec keySpec=new X509EncodedKeySpec(Base64Util.base64Dec(key));
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKey=keyFactory.generatePublic(keySpec);
            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
           return new String(cipher.doFinal(Base64Util.base64Dec(msg)),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
