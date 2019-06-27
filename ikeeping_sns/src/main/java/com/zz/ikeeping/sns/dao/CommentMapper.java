package com.zz.ikeeping.sns.dao;


import com.zz.ikeeping.sns.vo.VCommunityDetail;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentMapper {
    List<VCommunityDetail> selectComment(@RequestBody VCommunityDetail vCommunityDetail);

}