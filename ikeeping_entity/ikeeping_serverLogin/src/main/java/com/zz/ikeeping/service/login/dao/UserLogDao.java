package com.zz.ikeeping.service.login.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserLogDao {
    @Insert("insert into userlog(uid,flag,content,createtime) values(#{uid},2,#{content},now())")
    int save(@Param("uid") int uid, @Param("content") String content);
}
