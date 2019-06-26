package com.zz.ikeeping.enyity;

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