package com.zz.ikeeping.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Coupon {
    private Integer id;

    private String type;

    private Integer money;

    private Integer totalcount;

    private Date startdate;

    private Integer days;

    private Integer sendcount;

    private Integer usedcount;

}