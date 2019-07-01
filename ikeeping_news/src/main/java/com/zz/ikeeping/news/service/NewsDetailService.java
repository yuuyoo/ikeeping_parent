package com.zz.ikeeping.news.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.NewsDetail;

public interface NewsDetailService {
    //咨询绑定id
    R selectOne(Integer id);

    R update(NewsDetail detail);

    R push(String newstype);

    R pushgood(String name);
    //咨询id
    R sale(int id);
}
