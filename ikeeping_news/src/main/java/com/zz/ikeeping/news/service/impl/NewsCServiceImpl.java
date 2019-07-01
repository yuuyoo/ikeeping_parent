package com.zz.ikeeping.news.service.impl;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.util.JedisUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.News;
import com.zz.ikeeping.entity.NewsC;
import com.zz.ikeeping.news.dao.NewsCMapper;
import com.zz.ikeeping.news.dao.NewsMapper;
import com.zz.ikeeping.news.service.NewsCService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsCServiceImpl implements NewsCService {
    @Autowired
    private NewsCMapper cMapper;
    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public R update(NewsC c) {

        Integer nid = c.getNid();
        Integer pcount = c.getPcount();
        NewsC newsC = cMapper.selectOne(nid);
        Integer pcount2 = newsC.getPcount();
        String indexn = String.valueOf(nid);
        if (jedisUtil.exists(ProjectConfig.COMMENT + indexn)) {
            jedisUtil.set(ProjectConfig.COMMENT, "0");
        }
        String s = jedisUtil.get(ProjectConfig.COMMENT + indexn);
        int s1 = Integer.parseInt(s);
        NewsC news = new NewsC();
        news.setCcount(s1);//插入每次评论+1
        int sum = pcount + pcount2;
        news.setPcount(sum);
        news.setNid(nid);
        cMapper.update(news);
        return R.setOK();
    }

    @Override
    public R insert(NewsC c) {

        Integer nid = c.getNid();
        String indexn = String.valueOf(nid);
        String comment = c.getComment();
        Integer pcount = c.getPcount();
        if (!jedisUtil.exists(ProjectConfig.PRAISE + indexn) && jedisUtil.exists(ProjectConfig.COMMENT + indexn)) {
            jedisUtil.set(ProjectConfig.PRAISE + indexn, "0");
            jedisUtil.set(ProjectConfig.COMMENT + indexn, "0");
        }
        jedisUtil.incr(ProjectConfig.COMMENT + indexn);
        String s = jedisUtil.get(ProjectConfig.COMMENT + indexn);
        int ccount2 = Integer.parseInt(s);
        NewsC newsC = new NewsC();
        newsC.setPcount(pcount);
        newsC.setNid(nid);
        newsC.setComment(comment);
        newsC.setCcount(ccount2);
        update(newsC);//更新信息
        cMapper.insert(newsC);
        return R.setOK();
    }
}
