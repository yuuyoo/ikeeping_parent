package com.zz.ikeeping.ikeeping_api.service;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.CommunityDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "CommentProvider")
public interface SnsService {

    @GetMapping("sns/showTopicType.do")
    R selectTopicType();

    @GetMapping("sns/selectDetail.do")
    R selectDetail();

    @GetMapping("sns/showTopicComment.do")
    R showTopicComment(@RequestParam("id") int id);

    @GetMapping("sns/commentCount.do")
    R commentCount(@RequestParam("id") int id);

    @GetMapping("sns/newPublishTopicDetail.do")
    R newPublishTopicDetail(int cmid);

    @GetMapping("sns/showTopicAtMostComment.do")
    R showTopicAtMostComment(int cmid);

    @GetMapping("sns/allCommont.do")
    R allCommont();

    @PostMapping("sns/add.do")
    R add(@RequestBody CommunityDetail detail);

    @PostMapping("sns/addCommont.do")
    R addCommont(@RequestBody Comment comment);

    @PostMapping("sns/replyCommont.do")
    R replyCommont(@RequestBody Comment comment);

    @PutMapping("sns/topic.do")
    R topicPraise(@RequestParam("id") int id, @RequestParam("count") int count);

    @PutMapping("sns/commont.do")
    R commont(@RequestParam("id") int id, @RequestParam("count") int count);
}
