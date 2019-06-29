package com.zz.ikeeping.sns.service;

import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SnsService {

    List<Community> showTopicType();

    List<VCommunityDetail> selectDetail();

    List<VCommunityDetail> showTopicComment(int id);

    int commentCount(int id);

    //展示点赞数量-未写完
    int topicPraise(int id);

    List<VCommunityDetail> newPublishTopicDetail(int cmid);

    List<VCommunityDetail> showTopicAtMostComment(int cmid);

    /*------------------------------------------------------------------------------------------------------------------*/

    List<Comment> allCommont();

    int addCommont(Comment comment);

    void replyCommont(Comment comment);
}
