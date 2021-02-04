package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.CombinerBoxData;

public interface CombinerBoxDataMapper extends BaseMapper<CombinerBoxData> {
    int deleteByPrimaryKey(Object id);

    int insert(CombinerBoxData record);

    int insertSelective(CombinerBoxData record);

    CombinerBoxData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(CombinerBoxData record);

    int updateByPrimaryKey(CombinerBoxData record);
}