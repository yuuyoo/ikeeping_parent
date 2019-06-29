package com.zz.ikeeping.ikeeping_api.service;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    R newPublishTopicDetail(@RequestParam("cmid") int cmid);

    @GetMapping("sns/showTopicAtMostComment.do")
    R showTopicAtMostComment(@RequestParam("cmid") int cmid);

    /*------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("sns/allCommont.do")
    R allCommont();

    @PostMapping("sns/addCommont.do")
    R addCommont(@RequestBody Comment comment);

    @PostMapping("sns/replyCommont.do")
    R replyCommont(@RequestBody Comment comment);
}
