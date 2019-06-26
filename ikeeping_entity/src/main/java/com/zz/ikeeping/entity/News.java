package com.zz.ikeeping.entity;

import lombok.Data;

import java.util.Date;

@Data
public class News {
    private Integer id;

    private String newstype;

    private String imgurl;

    private String content;

    private Date pubtime;

}