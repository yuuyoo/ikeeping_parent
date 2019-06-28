package com.zz.ikeeping.service.login.service.impl;

import com.alibaba.fastjson.JSON;
import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.jwt.JwtUtil;
import com.zz.ikeeping.common.model.LoginToken;
import com.zz.ikeeping.common.util.EncryptionUtil;
import com.zz.ikeeping.common.util.IdGenerator;
import com.zz.ikeeping.common.util.JedisUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import com.zz.ikeeping.service.login.dao.UserDao;
import com.zz.ikeeping.service.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;
//    @Autowired
//    private UserLogDao logDao;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private IdGenerator idGenerator;

    //随时都可以登录，但是只允许在线一个（最新的） 10分钟 连续登录失败3次自动锁定账号1小时
    @Override
    public R login(String phone, String pass) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        R r;
        //先校验当前账号是否被锁定
        if(jedisUtil.exists(ProjectConfig.USERSD+phone)){
            r= R.setERROR("账号锁定中，剩余时间："+(jedisUtil.ttl(ProjectConfig.USERSD+phone)/60));
        }else {
            //1、校验用户名是否存在
            User user=userDao.selectByPhone(phone);
            if(user!=null){
                //2、校验密码是否正常
                if(Objects.equals(user.getPassword(), EncryptionUtil.RSAEnc(ProjectConfig.PASSRSAPRI,pass))){
                    //登录成功
                    LoginToken loginToken=new LoginToken();
                    loginToken.setPhone(phone);
                    loginToken.setUid(user.getUid());
                    loginToken.setId(idGenerator.nextId()+"");
                    //3、生成Token
                    String token= JwtUtil.createJWT(loginToken.getId(),JSON.toJSONString(loginToken));
                    //4、存储到服务器
                    //1、String 类型 user:phone  token
                    jedisUtil.setex(ProjectConfig.TOKENPHONE+phone,1800,token);
                    //2、String  类型 token phone 校验token是否有效
                    jedisUtil.setex(ProjectConfig.TOKENJWT+token,1800,JSON.toJSONString(user));
                    //3、Zset 记录每个手机号下的所有的Token  score:phone   value:令牌
                    //5、将Token
                    String s = simpleDateFormat.format(new Date());
                    jedisUtil.lpush(com.zz.ikeeping.service.login.common.ProjectConfig.LOGINLOG+user.getPhone(),user.getUid().toString(),"1",s,"登录成功，令牌生成");
                    //logDao.save(user.getUid(),"登录成功，令牌生成");
                    r= R.setOK("OK",token);
                }else {
                    String s1 = simpleDateFormat.format(new Date());
                    jedisUtil.lpush(com.zz.ikeeping.service.login.common.ProjectConfig.LOGINLOG+user.getPhone(),user.getUid().toString(),"2",s1,"登录失败，密码不正确");
                    //logDao.save(user.getUid(),"登录失败，密码有误");
                    r= R.setERROR("密码不正确");
                }
            }else {
                String s3 = simpleDateFormat.format(new Date());
                jedisUtil.lpush(com.zz.ikeeping.service.login.common.ProjectConfig.LOGINLOG+user.getPhone(),user.getUid().toString(),"3",s3,"登录失败，账号有误");
                //logDao.save(user.getUid(),"登录失败，账号有误");
                r= R.setERROR("是不是还没账号，快来注册");
            }
            if(r.getCode()!=1){
                String key= ProjectConfig.USERLOGINCOUNT+phone;
                jedisUtil.setex(key+"_"+System.currentTimeMillis(),600,"1");
                Set<String> set=jedisUtil.keys(key+"*");
                if(set.size()==3){
                    String s4 = simpleDateFormat.format(new Date());
                    jedisUtil.lpush(com.zz.ikeeping.service.login.common.ProjectConfig.LOGINLOG+user.getPhone(),user.getUid().toString(),"4",s4,"登录失败超过三次，账号被锁定");
                    //logDao.save(user.getUid(),"登录失败超过3次，账号被锁定");
                    //将当前账号冻结 1小时
                    jedisUtil.setex(ProjectConfig.USERSD+phone,3600,"10分钟连续失败三次冻结账号");
                    r= R.setERROR("连续多次账号或密码错误，账号被锁定，请1小时之后再来登录");
                }
            }
        }
        return r;
    }

    @Override
    public R checkLogin(String token) {
        //1、校验Token有效性
        if(JwtUtil.checkJWT(token)){
            //反解析 令牌 获取当初登录的手机号
            LoginToken loginToken=JSON.parseObject(JwtUtil.parseJWT(token),LoginToken.class);
            //获取当前手机号的令牌
            String t=jedisUtil.get(ProjectConfig.TOKENPHONE+loginToken.getPhone());
            //比对令牌
            if(Objects.equals(t,token)) {
                return R.setOK("有效", token);
            }else {
                return R.setERROR("已经在其他地方登录了");
            }
        }else {
            return R.setERROR("Token校验失败");
        }
    }

    @Override
    public R exitLogin(String token) {
        //1、校验Token有效性
        if(JwtUtil.checkJWT(token)){
            jedisUtil.del(ProjectConfig.TOKENJWT+token);
            //反解析 令牌 获取当初登录的手机号
            LoginToken loginToken=JSON.parseObject(JwtUtil.parseJWT(token),LoginToken.class);
            //获取当前手机号的令牌
            jedisUtil.del(ProjectConfig.TOKENPHONE+loginToken.getPhone());

            return R.setOK("退出成功",null);
        }else {
            return R.setERROR("Token校验失败");
        }
    }
}