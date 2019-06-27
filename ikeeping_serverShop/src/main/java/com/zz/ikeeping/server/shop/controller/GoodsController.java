package com.zz.ikeeping.server.shop.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.server.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods/list/level")
    public R levelList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count){
        return goodsService.getGoodsPageByType(typeid, page, count);
    }


}
