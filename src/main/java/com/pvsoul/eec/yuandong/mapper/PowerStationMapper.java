package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.dao.DayPowerDao;
import com.pvsoul.eec.yuandong.entity.PowerStation;

import java.util.List;

public interface PowerStationMapper extends BaseMapper<PowerStation> {
    int deleteByPrimaryKey(Object id);

    int insert(PowerStation record);

    int insertSelective(PowerStation record);

    PowerStation selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PowerStation record);

    int updateByPrimaryKey(PowerStation record);

    List<DayPowerDao> getDayPower(Object[] days);
}