package com.zz.ikeeping.message.core.dao;

import com.zz.ikeeping.message.core.entity.Message;

import java.util.List;

public interface MessageMapper {

    int insert(Message record);

    List<Message> selectAll();
}