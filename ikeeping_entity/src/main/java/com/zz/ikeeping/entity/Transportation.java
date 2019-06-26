package com.zz.ikeeping.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Transportation {
    private Integer id;

    private Integer uid;

    private Integer oid;

    private String fromaddress;

    private String curraddress;

    private Date datetime;

    private Integer aid;

}