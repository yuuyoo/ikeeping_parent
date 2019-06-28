package com.zz.ikeeping.sns.service;

import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SnsService {
    List<Community> showTopicType();

    List<VCommunityDetail> selectDetail();

    List<VCommunityDetail> showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id);

    Map<String, Object> addCommont(Comment comment);

    int commentCount(@RequestParam("uid") int uid, @RequestParam("id") int id);

    int topicPraise(@RequestParam("uid") int uid, @RequestParam("id") int id);

}
