package com.zz.ikeeping.common.vo;

import lombok.Data;

/**
 *@Author feri
 *@Date Created in 2019/6/3 11:47
 */
@Data
public class R {
    private int code;
    private String msg;
    private Object data;

    public static R setOK(){
        R r=new R();
        r.setCode(1);
        r.setMsg("OK");
        r.setData(null);
        return r;
    }
    public static R setOK(String msg, Object data){
        R r=new R();
        r.setCode(1);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    public static R setERROR(){
        R r=new R();
        r.setCode(1000);
        r.setMsg("ERROR");
        r.setData(null);
        return r;
    }
    public static R setERROR(String msg){
        R r=new R();
        r.setCode(1000);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

}
