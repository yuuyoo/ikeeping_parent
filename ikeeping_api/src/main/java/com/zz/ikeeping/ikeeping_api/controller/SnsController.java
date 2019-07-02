package com.zz.ikeeping.ikeeping_api.controller;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.Comment;
import com.zz.ikeeping.entity.CommunityDetail;
import com.zz.ikeeping.ikeeping_api.service.SnsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description = "社区入口")
@RequestMapping("/api/sns")
@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    @ApiOperation(value = "展示所有话题类型", produces = "application/json")
    @GetMapping("/showTopicType.do")
    public R selectTopicType() {
        return snsService.selectTopicType();
    }

    @ApiOperation(value = "展示所有话题类型中最新话题", produces = "application/json")
    @GetMapping("/selectDetail.do")
    public R selectDetail() {
        return snsService.selectDetail();
    }

    @ApiOperation(value = "最新发表", produces = "application/json")
    @ApiImplicitParam(name = "cmid", value = "话题类型id", required = true, defaultValue = "1")
    @GetMapping("/newPublishTopicDetail.do")
    public R newPublishTopicDetail(@RequestParam("cmid") int cmid) { return snsService.newPublishTopicDetail(cmid); }

    @ApiOperation(value = "最热回复", produces = "application/json")
    @ApiImplicitParam(name = "cmid", value = "话题类型id", required = true, defaultValue = "1")
    @GetMapping("/showTopicAtMostComment.do")
    public R showTopicAtMostComment(@RequestParam("cmid") int cmid) {
        return snsService.showTopicAtMostComment(cmid);
    }

    @ApiOperation(value = "xx话题下的前3条评论", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "xx话题id", required = true)
    @GetMapping("/showTopicComment.do")
    public R showTopicComment(@RequestParam("id") int id) {
        return snsService.showTopicComment(id);
    }

    @ApiOperation(value = "xx话题下的评论数量", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "xx话题id", required = true)
    @GetMapping("/commentCount.do")
    public R commentCount(@RequestParam("id") int id) {
        return snsService.commentCount(id);
    }

    @ApiOperation(value = "xx话题的浏览量", produces = "application/json")
    @ApiImplicitParam(name = "id", value = "xx话题id")
    @GetMapping("/pageViewCount.do")
    public R pageViewCount(@RequestParam("id") int id, HttpServletRequest request){
        String IP = request.getRemoteAddr();
        return snsService.pageViewCount(id, IP);
    }

    @ApiOperation(value = "查看所有评论", produces = "application/json")
    @GetMapping("/allCommont.do")
    public R allCommont() {
        return snsService.allCommont();
    }

    @ApiOperation(value = "新增内容分享", produces = "application/json")
    @PostMapping("/add.do")
    public R add(@RequestBody CommunityDetail detail){
        return snsService.add(detail);
    }

    @ApiOperation(value = "新增评论", produces = "application/json")
    @PostMapping("/addCommont.do")
    public R addCommont(@RequestBody Comment comment) {
        return snsService.addCommont(comment);
    }

    @ApiOperation(value = "回复评论", produces = "application/json")
    @PostMapping("/replyCommont.do")
    public R replyCommont(@RequestBody Comment comment) {
        return snsService.replyCommont(comment);
    }

    @ApiOperation(value = "点赞内容分享", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "话题id"),
            @ApiImplicitParam(name = "count", value = "数量")
    })
    @PutMapping("/topic.do")
    R topicPraise(@RequestParam("id") int id, @RequestParam("count") int count){
        return snsService.topicPraise(id, count);
    }

    @ApiOperation(value = "评论点赞", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论id"),
            @ApiImplicitParam(name = "count", value = "数量")
    })
    @PutMapping("/commont.do")
    R commont(@RequestParam("id") int id, @RequestParam("count") int count){
        return snsService.commont(id, count);
    }
}
