package com.zz.ikeeping.news.controller;


import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.News;
import com.zz.ikeeping.news.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Api(value = "咨讯操作",tags = "咨讯操作")
@Controller
@ResponseBody
public class NewsController {
   @Autowired
    private NewsService newsService;
    @ApiOperation(value = "添加",notes = "添加")
    @PostMapping("/news/add.do")
    public R add(@RequestBody  News news){
     return  newsService.insert(news);
    }
    @ApiOperation(value = "查询全部分页",notes = "查询全部")
    @PostMapping("/news/find.do")
    public R find( @RequestBody  Map<String,String> map){
        return newsService.queryPage(map);
    }

}
