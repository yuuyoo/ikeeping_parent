package com.zz.ikeeping.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer id;

    private Integer ruid;

    private Integer uid;

    private Integer cmid;

    private String content;

    private String imgurl;

}