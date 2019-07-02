package com.zz.ikeeping.sns.dao;

import com.zz.ikeeping.entity.CommunityDetail;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommunityDetailMapper {

    List<VCommunityDetail> selectDetail();

    //话题点赞数量
    int topicPraise(@RequestParam("id") int id, @RequestParam("count") int count);

    //新增发表
    int add(CommunityDetail detail);

    List<VCommunityDetail> newPublishTopicDetail(int cmid);

    List<VCommunityDetail> showTopicAtMostComment(int cmid);
}