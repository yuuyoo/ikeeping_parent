package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.ikeeping_api.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/api/goods/")
    public R levelList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count){
        return goodsService.levelList(typeid, page, count);
    }

    @GetMapping("/api/goods/price/asc")
    public R priceAscList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count){
        return goodsService.priceAscList(typeid, page, count);
    }
    @GetMapping("/api/goods/price/desc")
    public R priceDescList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count){
        return goodsService.priceDescList(typeid, page, count);
    }

    @GetMapping("/api/goods/sellcount/asc")
    public R sellAscList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count){
        return goodsService.sellAscList(typeid, page, count);
    }
    @GetMapping("/api/goods/sellcount/desc")
    public R sellDescList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count){
        return goodsService.sellDescList(typeid, page, count);
    }

    @GetMapping("/api/goods/info")
    public R detail(@RequestParam("gid") Integer gid){
        return goodsService.detail(gid);
    }


}
