package com.zz.ikeeping.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;

    private Integer uid;

    private Integer status;

    private Date datetime;

    private Double totalprice;

    private Date modtime;

    private Double goodsprice;

    private Integer aid;

}