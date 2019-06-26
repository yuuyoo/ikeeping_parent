package com.zz.ikeeping.enyity;

import lombok.Data;

import java.util.Date;

@Data
public class CommunityDetail {
    private Integer id;

    private Integer cmid;

    private Integer uid;

    private Date pubtime;

    private String location;

    private String content;

    private String imgurl;
}