package com.zz.ikeeping.service.login.service;

import com.zz.ikeeping.common.vo.R;
import org.springframework.web.bind.annotation.GetMapping;

public interface OrdersStstusService {
    //根据状态查询订单
    @GetMapping("/order/selectByStatus.do")
    R selectByStatus(int status, int uid);
}
