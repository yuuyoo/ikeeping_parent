package com.zz.ikeeping.server.user.service.impl;

import com.zz.ikeeping.common.exception.UserException;
import com.zz.ikeeping.common.util.*;
import com.zz.ikeeping.common.config.ProjectConfig;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;

import com.zz.ikeeping.message.core.service.MessageService;
import com.zz.ikeeping.server.user.dao.UserMapper;
import com.zz.ikeeping.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;

import javax.annotation.Resource;


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
            return R.setOK("当前手机号可用");
        }else {
            return R.setERROR("手机号已经注册过，请找回密码");

        }
    }

    @Override
    @Transactional(rollbackFor = {UserException.class})
    public R save(User user) throws UserException {
        if(userMapper.selectByPhone(user.getPhone())== null) {
            // 注册自动生成6位数字的密码
            int code = CodeUtil.createCode(6);
            try {
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

            return R.setOK("您的初始密码为：", code);
        } else  {
            return R.setERROR("您已经注册请找回密码");
        }


    }


    @Override
    public R insert(String phone, int code) {
        if (userMapper.selectByPhone(phone) != null) {
            return R.setERROR("手机号已经注册过，请找回密码");
        }
        String key=ProjectConfig.SMSCODE+phone;
            if(jedisUtil.exists(key)){
                String v=jedisUtil.get(key);
                if(v!=null){
                    if(Integer.parseInt(v)==code){
                        // 注册自动生成6位数字的密码
                        int password = CodeUtil.createCode(6);
                        System.out.println("mima"+password);
                        String name = String.valueOf(CodeUtil.createCode(8));
                        /* System.out.println("jedis"+jedisUtil.get(ProjectConfig.SMSCODE + phone));
                        System.out.println("code"+code);*/
                        System.out.println(Integer.parseInt(jedisUtil.get(ProjectConfig.SMSCODE + phone))==(code));
                        try {

                            if (jedisUtil.get(ProjectConfig.SMSCODE + phone).equals(String.valueOf(code))) {
                                User user = new User();
                                // 随机生成的密码进行加密
                                user.setPassword(EncryptionUtil.RSAEnc(ProjectConfig.PASSRSAPRI, Integer.toString(password)));
                                user.setUname(name);
                                user.setPhone(phone);
                                // 新增用户
                                userMapper.insert(user);
                                // 初始化其他


                            }
                        } catch (Exception e) {
                            try {
                                throw new UserException("注册异常" + e.getMessage());
                            } catch (UserException e1) {
                                e1.printStackTrace();
                            }
                        }
                        return R.setOK(name + "的初始密码为：", password);
                    }else {
                        return R.setERROR("验证码输入错误");
                    }
                }
            }

          return  R.setERROR("亲，验证码过期，请重新获取验证码");

    }





}
