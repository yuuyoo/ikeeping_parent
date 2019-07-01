package com.zz.ikeeping.sns.vo;

import com.zz.ikeeping.entity.CommunityDetail;
import lombok.Data;

@Data
public class VCommunityDetail extends CommunityDetail {
    //用户信息
    private Integer uid;
    private String imgurl;
    private String uname;

    //评论内容
    private String content;
}
