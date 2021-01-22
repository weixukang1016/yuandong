package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.Inverter;

public interface InverterMapper extends BaseMapper<Inverter> {
    int deleteByPrimaryKey(Object id);

    int insert(Inverter record);

    int insertSelective(Inverter record);

    Inverter selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Inverter record);

    int updateByPrimaryKey(Inverter record);
}