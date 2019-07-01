package com.zz.ikeeping.news.dao;

import com.zz.ikeeping.entity.NewsC;

public interface NewsCMapper {


    int insert(NewsC record);

    int update(NewsC record);

    NewsC selectOne(Integer nid);


}