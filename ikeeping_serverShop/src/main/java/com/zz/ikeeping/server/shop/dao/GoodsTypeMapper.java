package com.zz.ikeeping.server.shop.dao;

import com.zz.ikeeping.entity.GoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);

    List<GoodsType> selectAll();
}