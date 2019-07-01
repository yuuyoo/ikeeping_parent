package com.zz.ikeeping.server.user.dao;


import com.zz.ikeeping.entity.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSignDao {
    int insert(Sign sign);
    List<Sign> selectByUid(int uid);
    Sign selectByUidLast(int uid);
    //近 5天的签到数据 近30天的签到数据
    List<Sign> selectByUidDays(@Param("uid") int uid, @Param("days") int days);

}
