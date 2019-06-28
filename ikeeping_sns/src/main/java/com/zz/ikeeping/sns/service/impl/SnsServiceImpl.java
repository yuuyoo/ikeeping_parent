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

    @Override
    public List<Community> showTopicType() {
        return communityMapper.showTopicType();
    }

    @Override
    public Map<String, Object> showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        VCommunityDetail vCommunityDetail = new VCommunityDetail();

        vCommunityDetail.setUid(uid);
        vCommunityDetail.setId(id);

        List<VCommunityDetail> commentList = commentMapper.selectComment(vCommunityDetail);
        List<VCommunityDetail> detailList = communityDetailMapper.selectDetail();

        Map<String, Object> map = new HashMap<>();
        map.put("comment", commentList);
        map.put("detail", detailList);

        return map;
    }

    @Override
    public Map<String, Object> addCommont(Comment comment) {
        Comment comment1 = new Comment();

        comment1
        return null;
    }


    @Override
    public List<Comment> allCommont() {
        return commentMapper.all();
    }

    @Override
    public int commentCount(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        Map<String, Object> map = showTopicComment(uid, id);
        List<VCommunityDetail> list = (List<VCommunityDetail>) map.get("comment");
        return list.size();
    }
}
