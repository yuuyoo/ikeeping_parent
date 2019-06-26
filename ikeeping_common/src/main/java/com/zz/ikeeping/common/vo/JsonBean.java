package com.zz.ikeeping.common.vo;

import lombok.Data;

@Data
public class JsonBean {
    private Integer code;
    private String info;
    private Object data;

    public static JsonBean success() {
        JsonBean jsonBean = new JsonBean();
        jsonBean.setCode(1);
        jsonBean.setInfo("OK");
        jsonBean.setData(null);

        return jsonBean;
    }

    public static JsonBean success(String info, Object data) {
        JsonBean jsonBean = new JsonBean();
        jsonBean.setCode(1);
        jsonBean.setInfo(info);
        jsonBean.setData(data);

        return jsonBean;
    }

    public static JsonBean error(String info) {
        JsonBean jsonBean = new JsonBean();
        jsonBean.setCode(0);
        jsonBean.setInfo(info);
        jsonBean.setData(null);

        return jsonBean;
    }



}
