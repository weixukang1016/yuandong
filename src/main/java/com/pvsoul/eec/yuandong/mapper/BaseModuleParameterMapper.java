package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.BaseModuleParameter;

public interface BaseModuleParameterMapper extends BaseMapper<BaseModuleParameter> {
    int deleteByPrimaryKey(Object id);

    int insert(BaseModuleParameter record);

    int insertSelective(BaseModuleParameter record);

    BaseModuleParameter selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(BaseModuleParameter record);

    int updateByPrimaryKey(BaseModuleParameter record);
}