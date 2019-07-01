package com.zz.ikeeping.service.login.dao;

import com.zz.ikeeping.service.login.vo.CartVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CartDao {
    //查询购物车里的所有订单
    List<CartVo> selectAll();
    //用户购买后 根据id删除购物车的订单
    int deleteById(@RequestParam(value="id",required=true) List<Integer> id);

}
