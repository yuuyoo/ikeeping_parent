package com.zz.ikeeping.server.shop.service;

import com.zz.ikeeping.common.vo.R;

public interface GoodsService {
    R getGoodsPageByType(Integer typeid, Integer page, Integer count);

    R getById(Integer gid);
}
