package com.zz.ikeeping.server.shop.service;

import com.zz.ikeeping.common.vo.R;

public interface GoodsService {
    R getGoodsPageByType(Integer typeid, Integer page, Integer count);

    R getById(Integer gid);

    R getGoodsPageByTypePriceAsc(Integer typeid, Integer page, Integer count);

    R getGoodsPageByTypePriceDesc(Integer typeid, Integer page, Integer count);

    R getGoodsPageByTypeSellAsc(Integer typeid, Integer page, Integer count);

    R getGoodsPageByTypeSellDesc(Integer typeid, Integer page, Integer count);
}
