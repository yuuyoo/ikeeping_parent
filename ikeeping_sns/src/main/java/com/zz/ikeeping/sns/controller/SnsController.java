package com.zz.ikeeping.sns.controller;

import com.zz.ikeeping.enyity.Community;
import com.zz.ikeeping.sns.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    @GetMapping("sns/showTopicType.do")
    public JsonBean showTopicType() {
        List<Community> list = snsService.showTopicType();
        return JsonBean.success("OK", list);
    }

}
