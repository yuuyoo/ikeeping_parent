package com.zz.ikeeping.ikeeping_api.controller;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.ikeeping_api.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    //页面顶端展示话题类型
    @GetMapping("api/sns/showTopicType.do")
    public R selectTopicType() {
        return snsService.selectTopicType();
    }

    //展示最新发表的话题，不限话题类型
    @GetMapping("api/sns/selectDetail.do")
    public R selectDetail() {
        return snsService.selectDetail();
    }

    //xx话题类型下最新发表的话题
    @GetMapping("api/sns/newPublishTopicDetail.do")
    public R newPublishTopicDetail(@RequestParam("cmid") int cmid) {
        return snsService.newPublishTopicDetail(cmid);
    }

    //展示xx话题下的前3条评论
    @GetMapping("api/sns/showTopicComment.do")
    public R showTopicComment(@RequestParam("id") int id) {
        return snsService.showTopicComment(id);
    }

    //展示xx话题下的评论数量
    @GetMapping("api/sns/commentCount.do")
    public R commentCount(@RequestParam("id") int id) {
        return snsService.commentCount(id);
    }

    /*------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("api/sns/allCommont.do")
    public R allCommont() {
        return snsService.allCommont();
    }

    @PostMapping("api/sns/addCommont.do")
    public R addCommont(@RequestBody Comment comment) {
        return snsService.addCommont(comment);
    }

    @PostMapping("api/sns/replyCommont.do")
    public R replyCommont(@RequestBody Comment comment) {
        return snsService.replyCommont(comment);
    }
}
