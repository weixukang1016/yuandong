package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.BaseJinlangInvertCode;

public interface BaseJinlangInvertCodeMapper extends BaseMapper<BaseJinlangInvertCode> {
    int deleteByPrimaryKey(Object id);

    int insert(BaseJinlangInvertCode record);

    int insertSelective(BaseJinlangInvertCode record);

    BaseJinlangInvertCode selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(BaseJinlangInvertCode record);

    int updateByPrimaryKey(BaseJinlangInvertCode record);
}