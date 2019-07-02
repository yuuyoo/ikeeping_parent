package com.zz.ikeeping.ikeeping_api.service;

import com.zz.ikeeping.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ShopProvider")
public interface GoodsTypeService {
    @GetMapping("/goodstype/all")
    R all();
}
