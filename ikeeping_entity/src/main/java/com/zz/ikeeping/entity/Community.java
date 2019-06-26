package com.zz.ikeeping.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Community {
    private Integer cmid;

    private String cmtitle;

    private String cmimgurl;

    private Date cmtime;

}