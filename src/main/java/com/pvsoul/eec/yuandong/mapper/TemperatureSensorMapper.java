package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.TemperatureSensor;

public interface TemperatureSensorMapper extends BaseMapper<TemperatureSensor> {
    int deleteByPrimaryKey(Object id);

    int insert(TemperatureSensor record);

    int insertSelective(TemperatureSensor record);

    TemperatureSensor selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(TemperatureSensor record);

    int updateByPrimaryKey(TemperatureSensor record);
}