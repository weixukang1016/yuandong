package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.dao.DeviceStatusCountDao;
import com.pvsoul.eec.yuandong.entity.Transformer;

import java.util.List;

public interface TransformerMapper extends BaseMapper<Transformer> {
    int deleteByPrimaryKey(Object id);

    int insert(Transformer record);

    int insertSelective(Transformer record);

    Transformer selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(Transformer record);

    int updateByPrimaryKey(Transformer record);

    List<DeviceStatusCountDao> getDeviceStatusCount();
}