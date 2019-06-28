package com.zz.ikeeping.server.shop.service.impl;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.server.shop.dao.GoodsTypeMapper;
import com.zz.ikeeping.server.shop.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeDao;

    @Override
    public R selectAll() {
        return R.setOK("ok",goodsTypeDao.selectAll());
    }
}
