package com.zz.ikeeping.news.service.impl;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.util.JedisUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Goods;
import com.zz.ikeeping.entity.News;
import com.zz.ikeeping.entity.NewsDetail;
import com.zz.ikeeping.news.dao.NewsDetailMapper;
import com.zz.ikeeping.news.service.NewsDetailService;
import com.zz.ikeeping.news.vo.VNewDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Detail;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class NewsDetailServiceImpl implements NewsDetailService {
    @Autowired
    private NewsDetailMapper detailMapper;
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public R selectOne(Integer cid) {

        String s = String.valueOf(cid);
        if (jedisUtil.exists(ProjectConfig.NEWDETAIL + s)) {


            Long rcount = (jedisUtil.incr(ProjectConfig.NEWDETAIL + s));//自增1
            int rCount = new Long(rcount).intValue();//转long
            VNewDetail detail = new VNewDetail();
            detail.setReadcount(rCount);
            detail.setCid(cid);
            try {
                detailMapper.update(detail);
                return R.setOK("ok", detailMapper.selectOne(cid));
            } catch (Exception e) {
                return R.setERROR(e.getMessage());
            }
        } else {
            jedisUtil.set(ProjectConfig.NEWDETAIL + s, "0");

            return R.setOK("存入key成功");
        }
    }

    @Override
    public R update(NewsDetail detail) {

        Integer cid = detail.getCid();
        String dcontent = detail.getDetailcontent();
        NewsDetail detail1 = detailMapper.selectByK(cid);
        Integer rcount = detail1.getRewardcount();
        Integer rcount2 = detail.getRewardcount();
        NewsDetail detaila = new NewsDetail();
        Integer sum = rcount + rcount2;
        detaila.setRewardcount(sum);
        detaila.setDetailcontent(dcontent);
        detailMapper.update(detaila);
        return R.setOK("ok");
    }

    @Override
    public R push(String newstype) {
        List<News> list = detailMapper.push(newstype);
        News news = list.get((int) (Math.random() * list.size()));//随机筛选对象
        News new2 = list.get((int) (Math.random() * list.size()));
        List<News> list1 = new ArrayList<>();
        list1.add(news);
        list1.add(new2);
        return R.setOK("ok", list1);
    }

    @Override
    public R pushgood(String name) {
        List<Goods> list = detailMapper.pushgood(name);
        Goods goods = list.get((int) (Math.random() * list.size()));//随机筛选对象
        Goods goods1 = list.get((int) (Math.random() * list.size()));
        List<Goods> list1 = new ArrayList<>();
        list1.add(goods);
        list1.add(goods1);
        return R.setOK("ok", list1);
    }

    @Override
    public R sale(int id) {
        return R.setOK("ok", detailMapper.sale(id));
    }
}
