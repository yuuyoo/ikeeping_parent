package com.zz.ikeeping.news.vo;

import com.zz.ikeeping.entity.NewsDetail;
import lombok.Data;

import java.util.Date;

@Data
public class VNewDetail extends NewsDetail {
    private String imgurl;
    private Date pubtime;
    private String newstype;
    private String comment;
    private Integer ccount;
    private Integer pcount;
}
