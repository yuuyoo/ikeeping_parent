package com.zz.ikeeping.sns.service;

import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SnsService {

    List<Community> showTopicType();

    Map<String, Object> showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id);

    Map<String, Object> addCommont(@RequestParam("uid") int uid, @RequestParam("id") int id);

    List<Comment> allCommont();
}