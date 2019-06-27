package com.zz.ikeeping.server.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.server.shop.dto.GoodsDTO;
import com.zz.ikeeping.server.shop.dao.GoodsMapper;
import com.zz.ikeeping.server.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsDao;

    @Override
    public R getGoodsPageByType(Integer typeid, Integer page, Integer count) {
        PageHelper.startPage(page, count);
        List<GoodsDTO> goodsList = goodsDao.selectByTypeid(typeid);
        PageInfo<GoodsDTO> pageInfo = new PageInfo<>(goodsList);
        return R.setOK(pageInfo);
    }
}
