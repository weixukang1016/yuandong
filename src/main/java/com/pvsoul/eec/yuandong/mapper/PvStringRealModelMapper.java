package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.PvStringRealModel;

public interface PvStringRealModelMapper extends BaseMapper<PvStringRealModel> {
    int deleteByPrimaryKey(Object id);

    int insert(PvStringRealModel record);

    int insertSelective(PvStringRealModel record);

    PvStringRealModel selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvStringRealModel record);

    int updateByPrimaryKey(PvStringRealModel record);
}