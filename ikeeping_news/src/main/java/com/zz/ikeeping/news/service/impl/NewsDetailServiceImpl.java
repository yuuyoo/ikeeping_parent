package com.zz.ikeeping.news.service.impl;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.util.JedisUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.NewsDetail;
import com.zz.ikeeping.news.dao.NewsDetailMapper;
import com.zz.ikeeping.news.service.NewsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.soap.Detail;

@Service
public class NewsDetailServiceImpl implements NewsDetailService {
    @Autowired
    private NewsDetailMapper detailMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Override
    public R selectOne(Integer id,Integer wcount) {

        if(jedisUtil.exists(ProjectConfig.NewDetail)){

            Long rcount= (jedisUtil.incr(ProjectConfig.NewDetail));//自增1

            int rCount= new Long(rcount).intValue();//转long
            NewsDetail detail = new NewsDetail();
            detail.setReadcount(rCount);
            detail.setRewardcount(wcount);
            detail.setId(id);
            try {

                detailMapper.update(detail);
                return R.setOK("ok",detailMapper.selectOne(id));
            } catch (Exception e) {
                return R.setERROR(e.getMessage());
            }
        } else {
            jedisUtil.set(ProjectConfig.NewDetail,"0");

            return R.setOK("存入key成功");
        }

    }

    @Override
    public R update(NewsDetail detail) {

         detailMapper.update(detail);
         return  R.setOK("ok");
    }
}
