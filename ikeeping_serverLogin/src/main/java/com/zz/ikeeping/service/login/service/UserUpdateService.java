package com.zz.ikeeping.service.login.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface UserUpdateService {
    //更改用户信息
    R UserInfoUpdate(User user);
}
