package com.zz.ikeeping.sns.service;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SnsService {

    List<Community> showTopicType();

    List<VCommunityDetail> selectDetail();

    List<VCommunityDetail> showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id);

    int commentCount(@RequestParam("uid") int uid, @RequestParam("id") int id);

    int topicPraise(@RequestParam("uid") int uid, @RequestParam("id") int id);

    List<Comment> allCommont();

    int addCommont(Comment comment);

    void replyCommont(Comment comment);

    //话题点赞数量
    int topicPraise(@RequestParam("id") int id, @RequestParam("count") int count);

    //评论点赞数量
    int commont(@RequestParam("id") int id, @RequestParam("count") int count);
}
