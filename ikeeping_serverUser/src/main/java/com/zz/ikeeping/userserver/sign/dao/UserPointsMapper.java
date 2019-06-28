package com.zz.ikeeping.server.user.dao;

import com.zz.ikeeping.entity.Points;
import org.apache.ibatis.annotations.Param;

public interface UserPointsMapper {
    int insert(Points record);
    int update(@Param("uid") int uid, @Param("score") int score);

}