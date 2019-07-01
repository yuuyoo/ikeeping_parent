package com.zz.ikeeping.sns.controller;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.Community;
import com.zz.ikeeping.sns.dao.CommunityDetailMapper;
import com.zz.ikeeping.sns.service.SnsService;
import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    @GetMapping("sns/showTopicType.do")
    public R showTopicType() {
        List<Community> list = snsService.showTopicType();
        return R.setOK("OK",list);
    }

    @GetMapping("sns/selectDetail.do")
    public R selectDetail() {
        List<VCommunityDetail> list = snsService.selectDetail();
        return R.setOK("OK",list);
    }

    @GetMapping("sns/showTopicComment.do")
    public R showTopicComment(@RequestParam("id") int id) {
        List<VCommunityDetail> list = snsService.showTopicComment(id);
        return R.setOK("Ok", list);
    }

    @GetMapping("sns/commentCount.do")
    public R commentCount(@RequestParam("id") int id) {
        int count = snsService.commentCount(id);
        return R.setOK("OK",count);
    }

    @GetMapping("sns/newPublishTopicDetail.do")
    public R newPublishTopicDetail(int cmid) {
        List<VCommunityDetail> list = snsService.newPublishTopicDetail(cmid);
        return R.setOK("OK",list);
    }

    @GetMapping("sns/showTopicAtMostComment.do")
    public R showTopicAtMostComment(int cmid) {
        List<VCommunityDetail> list = snsService.showTopicAtMostComment(cmid);
        return R.setOK("OK",list);
    }


    @GetMapping("sns/allCommont.do")
    public R allCommont(){
        List<Comment> list = snsService.allCommont();
        return R.setOK("OK", list);
    }

    @PostMapping("sns/add.do")
    public R addCommont(CommunityDetailMapper detailMapper) {
        return R.setOK("OK",(snsService.add(detailMapper)));
    }
    @PostMapping("sns/addCommont.do")
    public R addCommont(@RequestBody Comment comment) {
        int i  = snsService.addCommont(comment);
        return R.setOK("OK", i);
    }

    @PostMapping("sns/replyCommont.do")
    public void replyCommont(@RequestBody Comment comment) {
        snsService.replyCommont(comment);
    }

    @PutMapping("sns/topic.do")
    public R topicPraise(@RequestParam("id") int id, @RequestParam("count") int count){
        int i = snsService.topicPraise(id, count);
        return R.setOK("OK", i);
    }

    @PutMapping("sns/commont.do")
    public R commont(@RequestParam("id") int id, @RequestParam("count") int count){
        int i = snsService.commont(id, count);
        return R.setOK("OK", i);
    }
}
