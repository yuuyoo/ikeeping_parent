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

    Map<String, Object> showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id);

    Map<String, Object> addCommont(@RequestParam("uid") int uid, @RequestParam("id") int id);

    int commentCount(@RequestParam("uid") int uid, @RequestParam("id") int id);

    List<Comment> allCommont();
}