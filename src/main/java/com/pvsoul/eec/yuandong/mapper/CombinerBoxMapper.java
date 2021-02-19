package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.CombinerBox;
import com.pvsoul.eec.yuandong.dao.DeviceStatusCount;

import java.util.List;

public interface CombinerBoxMapper extends BaseMapper<CombinerBox> {
    int deleteByPrimaryKey(Object id);

    int insert(CombinerBox record);

    int insertSelective(CombinerBox record);

    CombinerBox selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(CombinerBox record);

    int updateByPrimaryKey(CombinerBox record);

    List<DeviceStatusCount> getDeviceStatusCount();
}