package com.zz.ikeeping.sns.dao;

import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentMapper {

    int insert(Community community);

    List<VCommunityDetail> selectComment(@RequestBody VCommunityDetail vCommunityDetail);

    //新增评论、回复评论
    int add(Comment comment);
    //展示所有评论
    List<Comment> all();
}