package com.zz.ikeeping.ikeeping_api.sns.controller;

import com.zz.ikeeping.common.vo.JsonBean;
import com.zz.ikeeping.ikeeping_api.sns.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@RestController
public class SnsController {

    @Autowired
    private SnsService snsService;

    @GetMapping("api/sns/showTopicType.do")
    public JsonBean selectTopicType() {
        return snsService.selectTopicType();
    }
}
