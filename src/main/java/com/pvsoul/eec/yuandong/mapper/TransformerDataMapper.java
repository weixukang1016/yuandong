package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.TransformerData;

public interface TransformerDataMapper extends BaseMapper<TransformerData> {
    int deleteByPrimaryKey(Object id);

    int insert(TransformerData record);

    int insertSelective(TransformerData record);

    TransformerData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(TransformerData record);

    int updateByPrimaryKey(TransformerData record);
}