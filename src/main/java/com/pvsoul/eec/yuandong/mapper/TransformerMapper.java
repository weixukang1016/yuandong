package com.pvsoul.eec.yuandong.mapper;

import com.pvsoul.eec.yuandong.entity.Transformer;

public interface TransformerMapper {
    int deleteByPrimaryKey(Object id);

    int insert(Transformer record);

    int insertSelective(Transformer record);

    Transformer selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Transformer record);

    int updateByPrimaryKey(Transformer record);
}