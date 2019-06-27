package com.zz.ikeeping.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer rid;

    private Integer ruid;

    private String content;

    private String imgurl;

    private Integer browercount;

    private Integer transcount;

    private Integer clickcount;
}