package com.zz.ikeeping.ikeeping_api.service;

import com.zz.ikeeping.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ShopProvider")
public interface GoodsService {

    @GetMapping("/goods/list/level")
    R levelList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count);

    @GetMapping("/goods/list/price/asc")
    R priceAscList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count);
    @GetMapping("/goods/list/price/desc")
    R priceDescList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count);

    @GetMapping("/goods/list/sellcount/asc")
    R sellAscList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count);
    @GetMapping("/goods/list/sellcount/desc")
    R sellDescList(@RequestParam("typeid") Integer typeid, @RequestParam("page") Integer page, @RequestParam("count") Integer count);

    @GetMapping("/goods/detail")
    R detail(@RequestParam("gid") Integer gid);

}
