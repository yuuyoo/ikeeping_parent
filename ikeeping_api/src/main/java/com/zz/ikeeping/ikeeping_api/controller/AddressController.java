package com.zz.ikeeping.ikeeping_api.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Address;
import com.zz.ikeeping.ikeeping_api.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "地址管理接口", tags = "地址管理接口")
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 查询某登陆用户的所有非默认售货地址
     * @param token
     * @return
     */
    @ApiOperation("查询某登陆用户的所有非默认售货地址")
    @GetMapping("api/address/selectByUid")
    public R selectAddByUid(@RequestParam("token") String token) {
        return addressService.selectAddByUid(token);
    }

    /**
     * 添加用户收货地址信息
     * @param address
     * @return
     */
    @ApiOperation("添加用户收货地址信息")
    @PostMapping("address/insert.do")
    public R insert(@RequestBody Address address){
        return addressService.insert(address);
    }

    /**
     * 查询用户的默认收货地址
     * @param token
     * @return
     */
    @ApiOperation("查询用户的默认收货地址")
    @GetMapping("address/selectByDefult")
    public R selectByDefult(String token) {
        return addressService.selectByDefult(token);
    }

    /**
     * 修改收货地址信息
     * @param address
     * @return
     */
    @ApiOperation("修改收货地址信息")
    @PostMapping("address/updateadd")
    public R updateadd(@RequestBody Address address) {
        return addressService.updateadd(address);
    }

    /**
     *  删除指定id的地址信息
     * @param addid
     * @return
     */
    @ApiOperation("删除指定id的地址信息")
    @DeleteMapping("address/deleteById")
    public R deleteById(@RequestParam("addid")Integer addid) {
        return addressService.deleteById(addid);
    }
}
