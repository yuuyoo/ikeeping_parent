package com.zz.ikeeping.news.dao;

import com.zz.ikeeping.entity.Goods;
import com.zz.ikeeping.entity.News;
import com.zz.ikeeping.entity.NewsDetail;
import com.zz.ikeeping.entity.UserCoupon;
import com.zz.ikeeping.news.vo.VNewDetail;

import java.util.List;

public interface NewsDetailMapper {

    int insert(NewsDetail record);

    int insertSelective(NewsDetail record);

    VNewDetail selectOne(Integer cid);

    void update(NewsDetail detail);

    List<News> push(String newstype);

    List<Goods> pushgood(String name);
//咨询id
    UserCoupon sale(int id);

    NewsDetail selectByK(Integer cid);
}