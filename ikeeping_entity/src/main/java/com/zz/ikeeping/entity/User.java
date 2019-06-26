package com.zz.ikeeping.entity;

import lombok.Data;

@Data
public class User {
    private int uid;
    private String uname;
    private String phone;
    private String password;
    private String imgurl;
    private int flag;

}
