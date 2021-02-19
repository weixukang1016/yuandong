package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.dao.PvStringInfo;
import com.pvsoul.eec.yuandong.entity.PvStringData;

import java.util.List;

public interface PvStringDataMapper extends BaseMapper<PvStringData> {
    int deleteByPrimaryKey(Object id);

    int insert(PvStringData record);

    int insertSelective(PvStringData record);

    PvStringData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(PvStringData record);

    int updateByPrimaryKey(PvStringData record);

    List<PvStringInfo> getPvStringList(Integer deviceStatus);
}