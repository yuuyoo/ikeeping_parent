package com.zz.ikeeping.entity;

public class Address {
    private Integer addid;

    private Integer uid;

    private String addname;

    private String addtel;

    private String address;

    private Integer flag;

    public Integer getAddid() {
        return addid;
    }

    public void setAddid(Integer addid) {
        this.addid = addid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAddname() {
        return addname;
    }

    public void setAddname(String addname) {
        this.addname = addname == null ? null : addname.trim();
    }

    public String getAddtel() {
        return addtel;
    }

    public void setAddtel(String addtel) {
        this.addtel = addtel == null ? null : addtel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}