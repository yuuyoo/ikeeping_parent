package com.zz.ikeeping.entity;

import lombok.Data;

@Data
public class OrderDetail {
    private Integer id;

    private Integer oid;

    private Integer gid;

    private String odid;

    private Integer goodscount;

    private Double price;

}