package com.zz.ikeeping.service.login.dao;

import com.zz.ikeeping.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserInfoDao {
    //更新用户的信息
    int userInfoupdate(User user);
}
