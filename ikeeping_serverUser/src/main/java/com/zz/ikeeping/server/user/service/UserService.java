package com.zz.ikeeping.server.user.service;


import com.zz.ikeeping.common.exception.UserException;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;


public interface UserService {

    // 根据手机号查找用户有没有注册过
    R checkPhone(String phone);
    // 根据手机号注册
    R save(User user) throws UserException;
    // 根据手机验证码注册
    R verifyCode(String phone, int code) throws UserException;
    // 发送验证码， 记录在redis中
    R sendCode(String phone);


}
