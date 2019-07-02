package com.zz.ikeeping.service.login.service;

import com.zz.ikeeping.common.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CartVoService {
    //查询购物车的所有商品的信息，包括图片地址、商品名、价格、数量
    @GetMapping("/cart/all.do")
    R all();

    //根据id删除购物车的商品
    @GetMapping("/cart/delete.do")
    R delete(@RequestParam(value="id",required=true) List<Integer> id);
}
