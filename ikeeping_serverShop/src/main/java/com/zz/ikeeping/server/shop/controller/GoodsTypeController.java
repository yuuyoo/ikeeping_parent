package com.zz.ikeeping.server.shop.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.server.shop.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @GetMapping("/goodstype/all")
    public R all(){
        return goodsTypeService.selectAll();
    }

}
