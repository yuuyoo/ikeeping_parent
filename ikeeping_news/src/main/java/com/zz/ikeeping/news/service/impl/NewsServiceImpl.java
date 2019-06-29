package com.zz.ikeeping.news.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.ikeeping.common.vo.PageBean;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.News;
import com.zz.ikeeping.news.dao.NewsMapper;
import com.zz.ikeeping.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public R insert(News news) {
        newsMapper.insert(news);
        return R.setOK();
    }

    @Override
    public R queryPage(Map<String,String> map) {
        int page = Integer.parseInt(map.get("page"));
        int count =Integer.parseInt(map.get("count"));
        PageHelper.startPage(page,count);
        PageInfo<News> pageInfo = new PageInfo<News>(newsMapper.selectAll(map));
        PageBean<News> pageBean = new PageBean<>();
        pageBean.setCount(count);
        pageBean.setCurrPage(page);
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setTotalCount((int) pageInfo.getTotal());
        pageBean.setData(pageInfo.getList());
        return R.setOK("ok",pageBean);

    }
}
