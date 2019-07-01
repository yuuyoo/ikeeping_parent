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
}
