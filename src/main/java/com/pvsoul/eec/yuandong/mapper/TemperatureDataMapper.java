package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.TemperatureData;

public interface TemperatureDataMapper extends BaseMapper<TemperatureData> {
    int deleteByPrimaryKey(Object id);

    int insert(TemperatureData record);

    int insertSelective(TemperatureData record);

    TemperatureData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(TemperatureData record);

    int updateByPrimaryKey(TemperatureData record);
}