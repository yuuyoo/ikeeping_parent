package com.zz.ikeeping.message.core.entity;

import lombok.Data;

@Data
public class Message {
    private Long id;

    private Integer pcode;

    private Integer type;

    private String title;

    private String content;

    private String receive;

    private Integer flag;

}