package com.zz.ikeeping.service.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.jwt.JwtUtil;
import com.zz.ikeeping.common.util.EncryptionUtil;
import com.zz.ikeeping.common.util.JedisUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import com.zz.ikeeping.service.login.dao.UserInfoDao;
import com.zz.ikeeping.service.login.model.LoginToken;
import com.zz.ikeeping.service.login.service.UserUpdateService;
import com.zz.ikeeping.service.login.utils.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {


    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private  JedisUtil jedisUtil;

    @Override
    public R UserInfoUpdate(User user) {
       user.setPassword(EncryptionUtil.RSAEnc(ProjectConfig.PASSRSAPRI,user.getPassword()));

       int i = userInfoDao.userInfoupdate(user);
       if (i == 0){
           return R.setERROR("更新操作失败");
       }
       return R.setOK("更新成功",i);
    }
    public static void main(String[] args) {


        String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1OTQ5NDAzMjc3Nzc3OTYwOTYiLCJzdWIiOiJ7XCJpZFwiOlwiNTk0OTQwMzI3Nzc3Nzk2MDk2XCIsXCJwaG9uZVwiOlwiMTg1MzAyNTAzMjFcIixcInVpZFwiOjN9IiwiaWF0IjoxNTYxODg2NDI3LCJleHAiOjE1NjE4ODgyMjd9.EB6YaDSHYfjM6cG0vUhXHwmFFIN5p6GP4J7hC5tpsXY";
        LoginToken loginToken= UserTokenUtil.parseToken(JwtUtil.parseJWT(token));
        System.out.println(loginToken.getUid());
    }
}
