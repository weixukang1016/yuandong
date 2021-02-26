package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.PvStringRealCurrentModel;

public interface PvStringRealCurrentModelMapper extends BaseMapper<PvStringRealCurrentModel> {
    int deleteByPrimaryKey(Object id);

    int insert(PvStringRealCurrentModel record);

    int insertSelective(PvStringRealCurrentModel record);

    PvStringRealCurrentModel selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvStringRealCurrentModel record);

    int updateByPrimaryKey(PvStringRealCurrentModel record);
}