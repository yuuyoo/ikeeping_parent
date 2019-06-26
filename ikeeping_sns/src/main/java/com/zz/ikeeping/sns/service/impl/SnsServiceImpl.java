package com.zz.ikeeping.sns.service.impl;

import com.zz.ikeeping.enyity.Community;
import com.zz.ikeeping.sns.dao.CommunityMapper;
import com.zz.ikeeping.sns.service.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnsServiceImpl implements SnsService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public List<Community> showTopicType() {
        return communityMapper.showTopicType();
    }
}
