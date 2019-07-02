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
import org.springframework.web.bind.annotation.*;

@Api(value = "咨询详情",tags = "咨询详情")
@Controller
@ResponseBody
public class NewsDetailController {
    @Autowired
    private NewsDetailService  detailService;

    //rcount打赏数
    @ApiOperation(value = "详情查询",notes ="详情查询")
    @GetMapping("/newsd/find.do")
    public R find(int id){
        return detailService.selectAll(id);
    }


    @ApiOperation(value = "更新",notes ="详情查询")
    @PutMapping("/newsd/update.do")
    public R update(@RequestBody NewsDetail detail){
        return detailService.update(detail);
    }

    @ApiOperation(value = "推送健身类型",notes ="详情查询")
    @GetMapping("/newsd/push.do")
    public R push(String  newstype){

      return R.setOK("ok",detailService.push(newstype));
    }

    @ApiOperation(value = "推送营养餐",notes ="详情查询")
    @GetMapping("/newsd/pushgood.do")
    public R pushgood(String  name){
        return R.setOK("ok",detailService.pushgood(name));
    }

    @ApiOperation(value = "推荐优惠卷",notes ="详情查询")
    @GetMapping("/newsd/sale.do")  //详情id
    public R sale(int id){
        return R.setOK("ok",detailService.sale(id));
    }



}
