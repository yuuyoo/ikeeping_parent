package com.zz.ikeeping.service.login.service;


import com.zz.ikeeping.common.vo.R;


public interface LoginService {
    //实现登录
    R login(String phone, String pass);
    //校验登录有效性
    R checkLogin(String token);
    //注销
    R exitLogin(String token);

}
