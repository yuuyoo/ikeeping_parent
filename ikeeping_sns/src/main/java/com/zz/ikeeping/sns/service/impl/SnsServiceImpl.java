package com.zz.ikeeping.sns.service.impl;

import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import com.zz.ikeeping.sns.dao.CommentMapper;
import com.zz.ikeeping.sns.dao.CommunityDetailMapper;
import com.zz.ikeeping.sns.dao.CommunityMapper;
import com.zz.ikeeping.sns.service.SnsService;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //展示xx用户发表的xx话题下的评论，前3条
    @Override
    public List<VCommunityDetail> showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        VCommunityDetail vCommunityDetail = new VCommunityDetail();

        vCommunityDetail.setUid(uid);
        vCommunityDetail.setId(id);

        List<VCommunityDetail> commentList = commentMapper.selectComment(vCommunityDetail);
        return commentList;
    }

    @Override
    public Map<String, Object> addCommont(Comment comment) {
        return null;
    }


    @Override
    public List<Comment> allCommont() {
        return commentMapper.all();
    }

    //展示xx用户发表的xx话题下的评论数量
    @Override
    public int commentCount(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        List<VCommunityDetail> list = showTopicComment(uid, id);
        return list.size();
    }

    //展示xx用户发表的xx话题的点赞数量
    @Override
    public int topicPraise(int uid, int id) {
        return 0;
    }
}
