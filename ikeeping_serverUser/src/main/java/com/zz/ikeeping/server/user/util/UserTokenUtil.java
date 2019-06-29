package com.zz.ikeeping.server.user.util;


import com.alibaba.fastjson.JSON;
import com.zz.ikeeping.common.model.LoginToken;
import com.zz.ikeeping.entity.User;

public class UserTokenUtil {
    public static LoginToken parseToken(String json){
        if(json!=null){
            return JSON.parseObject(json,LoginToken.class);
        }else {
            return null;
        }
    }
    public static int parseTokenId(String json){
        if(json!=null){
            LoginToken user= JSON.parseObject(json,LoginToken.class);
            return user.getUid();
        }else {
            return 0;
        }
    }
    public static User parseUserToken(String json){
        if(json!=null){
            return JSON.parseObject(json,User.class);
        }else {
            return null;
        }
    }
    public static int parseUid(String json){
        if(json!=null){
            User user= JSON.parseObject(json,User.class);
            return user.getUid();
        }else {
            return 0;
        }
    }
}
