package com.zz.ikeeping.news.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.NewsDetail;

public interface NewsDetailService {

    R selectOne(Integer id,Integer rcount);

    R update(NewsDetail detail);
}
