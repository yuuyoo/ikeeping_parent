package com.zz.ikeeping.ikeeping_api.controller;


import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.ikeeping_api.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    @GetMapping("api/sns/showTopicType.do")
    public R selectTopicType() {
        return snsService.selectTopicType();
    }

    @GetMapping("api/sns/selectDetail.do")
    public R selectDetail() {
        return snsService.selectDetail();
    }

    @GetMapping("api/sns/showTopicComment.do")
    public R showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        return snsService.showTopicComment(uid, id);
    }

    @GetMapping("api/sns/commentCount.do")
    public R commentCount(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        return snsService.commentCount(uid, id);
    }

    @GetMapping("api/sns/allCommont.do")
    public R allCommont() {
        return snsService.allCommont();
    }

    @PostMapping("sns/addCommont.do")
    public R addCommont(@RequestBody Comment comment){
        return snsService.addCommont(comment);
    }

    @PostMapping("sns/replyCommont.do")
    public R replyCommont(@RequestBody Comment comment){
        return snsService.replyCommont(comment);
    }

    @PutMapping("sns/topic.do")
    R topicPraise(@RequestParam("id") int id, @RequestParam("count") int count){
        return snsService.topicPraise(id, count);
    }

    @PutMapping("sns/commont.do")
    R commont(@RequestParam("id") int id, @RequestParam("count") int count){
        return snsService.commont(id, count);
    }
}
