package com.zz.ikeeping.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sign {
    private Integer id;

    private Integer uid;

    private Integer score;

    private Integer extrascore;

    private Date createtime;

    private Integer days;

}