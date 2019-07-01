package com.zz.ikeeping.ikeeping_api.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "UserProvider")
public interface AddressService {
    @GetMapping("address/selectByUid.do")
    R selectAddByUid(@RequestParam("token") String token);

    // 添加指定用户的收货地址
    @PostMapping("address/insert.do")
    R insert(@RequestBody Address address);
    // 查询用户的默认收货地址
    @GetMapping("address/selectByDefult")
    R selectByDefult(@RequestParam("token") String token);
    // 修改收货地址信息
    @PostMapping("address/updateadd")
    R updateadd(@RequestBody Address address);
    // 删除指定id的地址信息
    @DeleteMapping("address/deleteById")
    R deleteById(@RequestParam("addid") Integer addid);
}