package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.PvStringRealPowerModel;

public interface PvStringRealPowerModelMapper extends BaseMapper<PvStringRealPowerModel> {
    int deleteByPrimaryKey(Object id);

    int insert(PvStringRealPowerModel record);

    int insertSelective(PvStringRealPowerModel record);

    PvStringRealPowerModel selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvStringRealPowerModel record);

    int updateByPrimaryKey(PvStringRealPowerModel record);
}