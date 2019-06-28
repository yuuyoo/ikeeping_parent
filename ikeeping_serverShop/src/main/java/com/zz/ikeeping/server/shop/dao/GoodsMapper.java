package com.zz.ikeeping.server.shop.dao;

import com.zz.ikeeping.entity.Goods;
import com.zz.ikeeping.server.shop.dto.GoodsDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsDTO> selectByTypeid(Integer typeid);

}