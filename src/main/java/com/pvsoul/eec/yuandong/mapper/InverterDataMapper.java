package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.InverterData;

public interface InverterDataMapper extends BaseMapper<InverterData> {
    int deleteByPrimaryKey(Object id);

    int insert(InverterData record);

    int insertSelective(InverterData record);

    InverterData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(InverterData record);

    int updateByPrimaryKey(InverterData record);
}