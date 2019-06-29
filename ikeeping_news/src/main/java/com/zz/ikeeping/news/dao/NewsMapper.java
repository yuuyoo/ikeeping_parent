package com.zz.ikeeping.news.dao;

import com.zz.ikeeping.entity.News;

import java.util.List;
import java.util.Map;

public interface NewsMapper {

    void insert(News record);

   List<News> selectAll(Map<String,String> map);


}