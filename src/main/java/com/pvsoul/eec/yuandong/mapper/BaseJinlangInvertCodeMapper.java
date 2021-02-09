package com.pvsoul.eec.yuandong.mapper;

import com.pvsoul.eec.yuandong.entity.BaseJinlangInvertCode;

public interface BaseJinlangInvertCodeMapper {
    int deleteByPrimaryKey(Object id);

    int insert(BaseJinlangInvertCode record);

    int insertSelective(BaseJinlangInvertCode record);

    BaseJinlangInvertCode selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(BaseJinlangInvertCode record);

    int updateByPrimaryKey(BaseJinlangInvertCode record);
}