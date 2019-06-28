package com.zz.ikeeping.server.user.service;


import com.zz.ikeeping.common.vo.R;


public interface UserSignService {
    R saveSign(String token);
    R queryByDays(String token, int days);
    R queryByUid(String token);
    R querySingle(String token);

}
