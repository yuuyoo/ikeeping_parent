package com.zz.ikeeping.ikeeping_api.controller;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.ikeeping_api.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    @GetMapping("api/sns/showTopicType.do")
    public R selectTopicType() {
        return snsService.selectTopicType();
    }

    @GetMapping("api/sns/showTopicComment.do")
    public R showTopicComment(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        return snsService.showTopicComment(uid, id);
    }

    @GetMapping("api/sns/commentCount.do")
    public R commentCount(@RequestParam("uid") int uid, @RequestParam("id") int id) {
        return snsService.commentCount(uid, id);
    }
}
