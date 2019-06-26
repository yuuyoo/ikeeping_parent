package com.zz.ikeeping.entity;

import lombok.Data;

@Data
public class Goods {
    private Integer id;

    private String name;

    private Integer price;

    private Integer flag;

    private String imgurl;

    private Integer goodstype;

    private Integer goodscount;

}