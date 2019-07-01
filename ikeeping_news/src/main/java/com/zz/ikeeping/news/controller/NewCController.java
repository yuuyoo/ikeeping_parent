package com.zz.ikeeping.news.controller;

import com.zz.ikeeping.common.vo.R;
import com.zz.ikeeping.entity.NewsC;
import com.zz.ikeeping.news.service.NewsCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
@Api(value = "评论操作",tags = "评论操作")
@Controller
@ResponseBody
public class NewCController {
    @Autowired
    private NewsCService cService;

    @ApiOperation(value = "更新数据",notes = "更新数据")
    @PutMapping("/newsc/update.do")
    public R update(@RequestBody NewsC c){
        cService.update(c);
        return R.setOK();
    }
    @ApiOperation(value = "插入数据",notes = "插入数据")
    @PostMapping("/newsc/add.do")
    public R add(@RequestBody  NewsC c){
        cService.insert(c);

        return R.setOK();
    }
}
