package com.zz.ikeeping.news.dao;

import com.zz.ikeeping.entity.NewsDetail;

public interface NewsDetailMapper {

    int insert(NewsDetail record);

    int insertSelective(NewsDetail record);

    NewsDetail selectOne(Integer id);

    void update(NewsDetail detail);

}