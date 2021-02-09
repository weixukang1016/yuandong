package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.DeviceStatusCount;
import com.pvsoul.eec.yuandong.entity.PvString;

import java.util.List;

public interface PvStringMapper extends BaseMapper<PvString> {
    int deleteByPrimaryKey(Object id);

    int insert(PvString record);

    int insertSelective(PvString record);

    PvString selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvString record);

    int updateByPrimaryKey(PvString record);

    List<DeviceStatusCount> getDeviceStatusCount();
}