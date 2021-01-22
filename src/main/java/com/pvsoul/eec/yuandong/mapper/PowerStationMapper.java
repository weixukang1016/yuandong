package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.PowerStation;

public interface PowerStationMapper extends BaseMapper<PowerStation> {
    int deleteByPrimaryKey(Object id);

    int insert(PowerStation record);

    int insertSelective(PowerStation record);

    PowerStation selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PowerStation record);

    int updateByPrimaryKey(PowerStation record);
}