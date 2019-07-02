package com.zz.ikeeping.server.user.dao;

import com.zz.ikeeping.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {

    // 添加收货地址
    int insert(Address address);

    // 根据用户id 查找所有用户地址
    List<Address> selectByUid(Integer uid);

    // 查询指定用户非默认收货地址
    Address selectByDefault(Integer uid);
    // 查询某一收货地址
    Address selectByAddid(Integer addid);

    // 修改默认收货地址
    void updateDefault(@Param("addid") Address addid);

    // 修改收货地址信息
    void update( Address address);
    // 删除指定的收货地址
    void deleteById(Integer addid);



}