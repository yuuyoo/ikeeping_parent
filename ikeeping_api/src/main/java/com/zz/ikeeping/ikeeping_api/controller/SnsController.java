package com.zz.ikeeping.ikeeping_api.controller;


import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.CommunityDetail;
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

    //XX类型下最多评论的话题
    @GetMapping("api/sns/showTopicAtMostComment.do")
    public R showTopicAtMostComment(@RequestParam("cmid") int cmid) {
        return snsService.showTopicAtMostComment(cmid);
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

    // 查看所有评论
    @GetMapping("api/sns/allCommont.do")
    public R allCommont() {
        return snsService.allCommont();
    }


    // 新增内容分享
    @PostMapping("sns/add.do")
    public R add(@RequestBody CommunityDetail detail){
        return snsService.add(detail);
    }

    // 新增评论
    @PostMapping("api/sns/addCommont.do")
    public R addCommont(@RequestBody Comment comment) {
        return snsService.addCommont(comment);
    }

    // 回复评论
    @PostMapping("api/sns/replyCommont.do")
    public R replyCommont(@RequestBody Comment comment) {
        return snsService.replyCommont(comment);
    }

    // 点赞内容分享
    @PutMapping("sns/topic.do")
    R topicPraise(@RequestParam("id") int id, @RequestParam("count") int count){
        return snsService.topicPraise(id, count);
    }

    // 评论点赞
    @PutMapping("sns/commont.do")
    R commont(@RequestParam("id") int id, @RequestParam("count") int count){
        return snsService.commont(id, count);
    }
}
