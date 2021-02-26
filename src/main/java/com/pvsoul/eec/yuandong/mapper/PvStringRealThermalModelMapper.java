package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.PvStringRealThermalModel;

public interface PvStringRealThermalModelMapper extends BaseMapper<PvStringRealThermalModel> {
    int deleteByPrimaryKey(Object id);

    int insert(PvStringRealThermalModel record);

    int insertSelective(PvStringRealThermalModel record);

    PvStringRealThermalModel selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvStringRealThermalModel record);

    int updateByPrimaryKey(PvStringRealThermalModel record);
}