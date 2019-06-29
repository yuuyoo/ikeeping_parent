package com.zz.ikeeping.server.user.dao;


import com.zz.ikeeping.entity.User;

public interface UserMapper {

    // 注册手机号
    int insert(User record);
    // 根据手机号查找用户有没有注册过
    User selectByPhone(String phone);
   /* // 根据手机号完善用户信息
    User updeteByPhone(String phone);*/



}