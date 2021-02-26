package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.PvStringRealTjModel;

public interface PvStringRealTjModelMapper extends BaseMapper<PvStringRealTjModel> {
    int deleteByPrimaryKey(Object id);

    int insert(PvStringRealTjModel record);

    int insertSelective(PvStringRealTjModel record);

    PvStringRealTjModel selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvStringRealTjModel record);

    int updateByPrimaryKey(PvStringRealTjModel record);
}