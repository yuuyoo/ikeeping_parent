package com.zz.ikeeping.entity;

import lombok.Data;

@Data
public class NewsC {
    private Integer id;

    private Integer nid;

    private String comment;
    private Integer pcount;//点赞数

    private  Integer ccount;//评论数


}