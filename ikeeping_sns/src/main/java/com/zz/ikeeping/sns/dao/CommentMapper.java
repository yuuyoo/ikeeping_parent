package com.zz.ikeeping.sns.dao;


import com.zz.ikeeping.enyity.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> selectComment();
}