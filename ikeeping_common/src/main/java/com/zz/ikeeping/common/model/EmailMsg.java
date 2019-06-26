package com.zz.ikeeping.common.model;

public class EmailMsg {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String company;//公司名称
    private String title;//邮箱标题
    private String content;//邮箱内容
    private String email;//接收人

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}