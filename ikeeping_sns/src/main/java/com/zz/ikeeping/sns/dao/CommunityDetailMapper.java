package com.zz.ikeeping.sns.dao;

import com.zz.ikeeping.sns.vo.VCommunityDetail;

import java.util.List;

public interface CommunityDetailMapper {
    List<VCommunityDetail> selectDetail();

    List<VCommunityDetail> newPublishTopicDetail(int cmid);


}