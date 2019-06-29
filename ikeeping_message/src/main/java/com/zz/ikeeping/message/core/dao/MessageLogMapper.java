package com.zz.ikeeping.message.core.dao;

import com.zz.ikeeping.message.core.entity.MessageLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageLogMapper {

    int insert(MessageLog record);
    List<MessageLog> selectByPage(@Param("index") int index, @Param("count") int count);
    long selectCount();
}