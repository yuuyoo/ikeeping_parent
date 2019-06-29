package com.zz.ikeeping.news.controller;

import com.zz.ikeeping.common.config.ProjectConfig;
import com.zz.ikeeping.common.util.JedisUtil;
import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.NewsDetail;
import com.zz.ikeeping.news.service.NewsDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(value = "咨询详情",tags = "咨询详情")
@Controller
@ResponseBody
public class NewsDetailController {
    @Autowired
    private NewsDetailService  detailService;

    //rcount打赏数
    @ApiOperation(value = "详情查询",notes ="详情查询")
    @GetMapping("/find.do")
    public R find(int id,int wcount){
       return detailService.selectOne(id,wcount);
    }

}
