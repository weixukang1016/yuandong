package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.entity.PvString;

public interface PvStringMapper extends BaseMapper<PvString> {
    int deleteByPrimaryKey(Object id);

    int insert(PvString record);

    int insertSelective(PvString record);

    PvString selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvString record);

    int updateByPrimaryKey(PvString record);
}