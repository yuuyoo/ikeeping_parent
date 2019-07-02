package com.zz.ikeeping.service.login.dao;

import com.zz.ikeeping.entity.Order;

import java.util.List;

public interface OrdersStatusDao {
/**
 * @author : nick
 * @date : 2019-07-01 20:47
 **/

    //根据状态查询相应的订单
    List<Order> selectByStatus(int status ,int uid);

}
