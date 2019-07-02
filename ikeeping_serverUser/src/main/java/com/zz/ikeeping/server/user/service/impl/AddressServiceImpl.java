package com.zz.ikeeping.server.user.service.impl;

import com.zz.ikeeping.common.jwt.JwtUtil;
import com.zz.ikeeping.common.model.LoginToken;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Address;
import com.zz.ikeeping.server.user.dao.AddressMapper;
import com.zz.ikeeping.server.user.service.AddressService;
import com.zz.ikeeping.server.user.util.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public R selectById(String token) {
        // 解析token获取uid
        LoginToken loginToken = UserTokenUtil.parseToken(JwtUtil.parseJWT(token));
        Integer uid = loginToken.getUid();
        System.out.println(uid);
        List<Address> list =  addressMapper.selectByUid(uid);
        list.toString();
        return R.setOK("OK",  addressMapper.selectByUid(uid));
    }

    @Override
    public R selectByDefult(String token) {
        LoginToken loginToken = UserTokenUtil.parseToken(JwtUtil.parseJWT(token));
        Integer uid = loginToken.getUid();
        return R.setOK("OK", addressMapper.selectByDefault(uid));
    }

    @Override
    public R deleteById(Integer addid) {
        Address address = addressMapper.selectByAddid(addid);
        if(address != null) {
            addressMapper.deleteById(addid);
            return R.setOK("OK");
        } else {
            return  R.setERROR("该信息不存在");
        }

    }

    @Override
    public R insert(Address address) {

        addressMapper.insert(address);
        return R.setOK("添加地址成功");
    }

    /*@Override
    public R updateDefult(Integer addid) {
        addressMapper.updateDefault(addid);
        return R.setOK("OK");
    }*/


    @Override
    public R updateByAddid(Address address) {

        addressMapper.update(address);
        return R.setOK("OK");
    }
}
