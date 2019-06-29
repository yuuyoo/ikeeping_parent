package com.zz.ikeeping.server.user.dao;


import com.zz.ikeeping.entity.Sign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSignDao {
    //签到
    int insert(Sign sign);
    //查询客户签到信息
    List<Sign> selectByUid(int uid);
    //查询第一页签到
    Sign selectByUidLast(int uid);
    //近 5天的签到数据 近30天的签到数据
    List<Sign> selectByUidDays(@Param("uid") int uid, @Param("days") int days);

}
