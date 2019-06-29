package com.zz.ikeeping.message.core.dao;

import com.zz.ikeeping.message.core.entity.MsgReceive;

import java.util.List;

public interface MsgReceiveMapper {

    int insert(MsgReceive record);

    List<MsgReceive> selectAll();
}