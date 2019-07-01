package com.zz.ikeeping.server.user.service.impl;

import com.zz.ikeeping.common.exception.UserException;
import com.zz.ikeeping.common.util.*;
import com.zz.ikeeping.common.config.ProjectConfig;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import com.zz.ikeeping.server.user.dao.UserMapper;
import com.zz.ikeeping.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public R checkPhone(String phone) {
        User user=userMapper.selectByPhone(phone);
        if(user!=null){
            return R.setERROR("手机号已经注册过，请找回密码");
        }else {
            return R.setOK();
        }
    }

    @Override
    @Transactional(rollbackFor = {UserException.class})
    public R save(User user) throws UserException {
        try {
            // 注册自动生成6位数字的密码
            int code = CodeUtil.createCode(6);
            System.out.println(code);
            // 随机生成的密码进行加密
            user.setPassword(EncryptionUtil.RSAEnc(ProjectConfig.PASSRSAPRI,Integer.toString(code)));
            user.setUname(String.valueOf(user.getUid()));
            // 新增用户
            userMapper.insert(user);
            // 初始化其他
        } catch (Exception e) {
            throw new UserException("用户注册异常"+ e.getMessage());
        }

        return R.setOK("您的初始密码为：",user.getPassword());
    }


    @Override
    public R verifyCode(String phone, int code) throws UserException {
        if (userMapper.selectByPhone(phone) != null) {
            return R.setERROR("手机号已经注册过，请找回密码");
        }

        if (jedisUtil.exists(ProjectConfig.SMSCODE + phone) == false) {
            return R.setERROR("验证码已过期请重新发送");
        }
        // 注册自动生成6位数字的密码
        int password = CodeUtil.createCode(6);
        System.out.println(password);
        try {

            if (jedisUtil.get(ProjectConfig.SMSCODE + phone).equals(code)) {
                User user = new User();


                // 随机生成的密码进行加密
                user.setPassword(EncryptionUtil.RSAEnc(ProjectConfig.PASSRSAPRI, Integer.toString(password)));
                user.setUname(String.valueOf(user.getUid()));
                // 新增用户
                userMapper.insert(user);
                // 初始化其他
            }
        } catch (Exception e) {
            throw new UserException("注册异常" + e.getMessage());
        }
        return R.setOK("您的初始密码为：", password);
    }


    @Override
    public R sendCode(String phone) {
        int code = 0;
        boolean isflag = false;
        try {
            int count = 0;

            if (userMapper.selectByPhone(phone) != null) {
                return R.setERROR("手机号已经注册过，请找回密码");
            }
            String c = jedisUtil.get(ProjectConfig.SMSPREDAY + phone);
            if (c != null && c.matches("[0-9]{1,2}")) {
                count = Integer.parseInt(c);
                if (count >= 20) {
                    return R.setERROR("亲，一个手机号一天只能发送指定数量的短信");
                }
            }
            //一个手机号一分钟只能发送1次
            if (jedisUtil.exists(ProjectConfig.SMSPRELIMIT + phone)) {
                //存在说明1分钟只能发送过短信
                return R.setERROR("一个手机号一分钟只能发送1次短信");
            }
            //检查是否可以发送
            code = CodeUtil.createNum(6);
            int flag = 3;
            String info = "";
            isflag = false;

            if (jedisUtil.exists(ProjectConfig.SMSCODE + phone)) {
                //上次的验证码还没失效
                code = Integer.parseInt(jedisUtil.get(ProjectConfig.SMSCODE + phone));
            } else {
                code = CodeUtil.createNum(6);
                isflag = true;
            }
            info = "发送短信验证码：" + code;
            //验证码 三分钟有效
            SmsUtil.mobileQuery(phone, code);
            System.out.println("code"+ code +"phone"+ phone);
            flag = 1;//发送成功
            //1分钟
            jedisUtil.setex(ProjectConfig.SMSPRELIMIT + phone, 60, "短信发送限制");
            //1天
            jedisUtil.setex(ProjectConfig.SMSPREDAY + phone, TimeUtil.getLastSeconds(), (count + 1) + "");
            if (isflag) {
                //记录验证码
                jedisUtil.setex(ProjectConfig.SMSCODE + phone, 180, code + "");
            }
            // 初始化购物车
            // 初始化积分
            // 初始化
            return R.setOK();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return R.setERROR();
        }



    }



}
