package com.zz.ikeeping.server.shop.dto;

import lombok.Data;

@Data
public class GoodsDTO {
    private Integer id;

    private String name;

    private Integer price;

    private String imgurl;

    private Integer goodstype;

    private Integer sellcount;
}
