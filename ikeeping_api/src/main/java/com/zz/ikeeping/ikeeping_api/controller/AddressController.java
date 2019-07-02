package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Address;
import com.zz.ikeeping.ikeeping_api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 查询某登陆用户的所有非默认售货地址
     * @param token
     * @return
     */
    @GetMapping("api/address/selectByUid")
    public R selectAddByUid(@RequestParam("token") String token) {
        return addressService.selectAddByUid(token);
    }

    /**
     * 添加用户收货地址信息
     * @param address
     * @return
     */
    @PostMapping("address/insert.do")
    public R insert(@RequestBody Address address){
        return addressService.insert(address);
    }

    /**
     * 查询用户的默认收货地址
     * @param token
     * @return
     */
    @GetMapping("address/selectByDefult")
    public R selectByDefult(String token) {
        return addressService.selectByDefult(token);
    }

    /**
     * 修改收货地址信息
     * @param address
     * @return
     */
    @PostMapping("address/updateadd")
    public R updateadd(@RequestBody Address address) {
        return addressService.updateadd(address);
    }

    /**
     *  删除指定id的地址信息
     * @param addid
     * @return
     */
    @DeleteMapping("address/deleteById")
    public R deleteById(@RequestParam("addid")Integer addid) {
        return addressService.deleteById(addid);
    }
}
