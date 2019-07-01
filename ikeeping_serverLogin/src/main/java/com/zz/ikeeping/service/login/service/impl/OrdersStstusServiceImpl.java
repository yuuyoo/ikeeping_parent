package com.zz.ikeeping.service.login.service.impl;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Order;
import com.zz.ikeeping.service.login.dao.OrdersStatusDao;
import com.zz.ikeeping.service.login.service.OrdersStstusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : nick
 * @date : 2019-07-01 21:01
 **/
@Service
public class OrdersStstusServiceImpl implements OrdersStstusService {

    @Autowired
    private OrdersStatusDao ordersStatusDao;

    @Override
    public R selectByStatus(int status, int uid) {
        List<Order> orders = ordersStatusDao.selectByStatus(status, uid);
        return R.setOK("OK",orders);
    }


}
