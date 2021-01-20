package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.MeteorologicalData;

public interface MeteorologicalDataMapper extends BaseMapper<MeteorologicalData> {
    int deleteByPrimaryKey(Object id);

    int insert(MeteorologicalData record);

    int insertSelective(MeteorologicalData record);

    MeteorologicalData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(MeteorologicalData record);

    int updateByPrimaryKey(MeteorologicalData record);
}