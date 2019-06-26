package com.zz.ikeeping.common.jwt;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.util.TimeUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtil {
    /**
     * 基于JWT,生成令牌
     * id 令牌序号
     * content 存储的内容 */
    public static String createJWT(String id,String content){
        //获取指定签名加密算法的枚举对象
        SignatureAlgorithm algorithm=SignatureAlgorithm.HS256;
        JwtBuilder builder=Jwts.builder();
        builder.setId(id);
        builder.setSubject(content); //sub
        builder.setIssuedAt(new Date());//开始时间
        builder.setExpiration(TimeUtil.getMinutes(ProjectConfig.JWTTIME));//失效时间
        builder.signWith(algorithm,createKey());
        return builder.compact();
    }
    //生成秘钥
    private static SecretKey createKey(){
      byte[] dataKey=ProjectConfig.JWTKEY.getBytes();
        SecretKey key=new SecretKeySpec(dataKey,0,dataKey.length,"AES");
        return key;
    }
    //校验令牌是否合法
    public static boolean checkJWT(String token){
        SecretKey key=createKey();
        try{
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //解析Token
    public static String parseJWT(String token){
        SecretKey key=createKey();
        try{
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims.getSubject();
        }catch (Exception e){
            return null;
        }
    }
    //更新失效时间
    public static String updateJWT(String token){
        SecretKey key=createKey();
        try{
            SignatureAlgorithm algorithm=SignatureAlgorithm.HS256;
            Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            claims.setIssuedAt(new Date());
            claims.setExpiration(TimeUtil.getMinutes(ProjectConfig.JWTTIME));
            JwtBuilder builder=Jwts.builder();
            builder.addClaims(claims);
            builder.signWith(algorithm,key);
            return builder.compact();
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
//        String token=createJWT("1001","{'name':'zhangsan','phone':'18515990152'}");
//        System.out.println(token);
//        System.out.println("解析："+parseJWT(token));
//        String token1=updateJWT(token);
//        System.out.println("更新之后："+token1);
        System.out.println(checkJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1ODkxMzMxMTk1Njc2OTE3NzYiLCJzdWIiOiJ7XCJpZFwiOlwiNTg5MTMzMTE5NTY3NjkxNzc2XCIsXCJwaG9uZVwiOlwiMTg1MTU5OTAxNTRcIixcInVpZFwiOjZ9IiwiaWF0IjoxNTYwNTAxODgxLCJleHAiOjE1NjA1MDM2ODF9.iwZfw0EkaAxtuo56-SaVNZnCXZL5E9f0FFt_oXe4aws"));
    }
}
