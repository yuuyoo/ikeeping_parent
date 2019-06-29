package com.zz.ikeeping.sns.service.impl;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import com.zz.ikeeping.sns.dao.CommentMapper;
import com.zz.ikeeping.sns.dao.CommunityDetailMapper;
import com.zz.ikeeping.sns.dao.CommunityMapper;
import com.zz.ikeeping.sns.service.SnsService;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SnsServiceImpl implements SnsService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private CommunityDetailMapper communityDetailMapper;

    @Autowired
    private CommentMapper commentMapper;

    //页面顶端展示话题类型
    @Override
    public List<Community> showTopicType() {
        return communityMapper.showTopicType();
    }

    //展示所有已发表的话题
    @Override
    public List<VCommunityDetail> selectDetail() {
        return communityDetailMapper.selectDetail();
    }

    //展示xx话题下的前3条评论
    @Override
    public List<VCommunityDetail> showTopicComment(int id) {
        return commentMapper.selectComment(id);
    }

    //展示xx话题下的评论数量
    @Override
    public int commentCount(int id) {
        List<VCommunityDetail> list = showTopicComment(id);
        return list.size();
    }

    //展示xx话题的点赞数量
    @Override
    public int topicPraise(int id) {
        return 0;
    }

    //xx话题类型下最新发表的话题
    @Override
    public List<VCommunityDetail> newPublishTopicDetail(int cmid) {
        return communityDetailMapper.newPublishTopicDetail(cmid);
    }

    //XX类型下最多评论的话题
    @Override
    public List<VCommunityDetail> showTopicAtMostComment(int cmid) {
        return communityDetailMapper.showTopicAtMostComment(cmid);
    }

    // 查看所有评论
    @Override
    public List<Comment> allCommont() {
        return commentMapper.all();
    }

    // 新增评论
    @Override
    public int addCommont(Comment comment) {
        return commentMapper.addCommont(comment);
    }

    // 回复评论
    @Override
    public void replyCommont(Comment comment) {
        commentMapper.replyCommont(comment);
    }

    //展示xx用户发表的xx话题的点赞数量
    @Override
    public int topicPraise(@RequestParam("id") int id, @RequestParam("count") int count) {
        count = count + 1;
        return communityDetailMapper.topicPraise(id, count);
    }

    //展示xx用户发表的xx话题下评论的点赞数量
    @Override
    public int commont(@RequestParam("id") int id, @RequestParam("count") int count) {
        count = count + 1;
        return commentMapper.commont(id, count);
    }
}
