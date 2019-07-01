package com.zz.ikeeping.server.user.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Address;
import com.zz.ikeeping.server.user.service.AddressService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;
    // 查询指定用户的所有非默认地址
    @GetMapping("address/selectByUid.do")
    public R selectAddByUid(@RequestParam("token") String token){
        return addressService.selectById(token);
    }
    // 添加指定用户的收货地址
    @PostMapping("address/insert.do")
    public R insert(@RequestBody Address address) {
        return  addressService.insert(address);
    }
    // 查询用户的默认收货地址
    @GetMapping("address/selectByDefult")
    public R selectByDefult(String token) {
        return addressService.selectByDefult(token);
    }
   // 修改收货地址信息
    @PostMapping("address/updateadd")
    public R updateadd(@RequestBody Address address) {
        return addressService.updateByAddid(address);
    }
    // 删除指定id的地址信息
    @DeleteMapping("address/deleteById")
    public R deleteById(@RequestParam("addid")Integer addid) {
        return addressService.deleteById(addid);
    }
}
