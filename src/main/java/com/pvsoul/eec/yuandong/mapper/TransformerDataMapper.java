package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.dao.QueryDeviceInfoDao;
import com.pvsoul.eec.yuandong.dao.TransformerInfoDao;
import com.pvsoul.eec.yuandong.entity.TransformerData;

import java.util.List;

public interface TransformerDataMapper extends BaseMapper<TransformerData> {
    int deleteByPrimaryKey(Object id);

    int insert(TransformerData record);

    int insertSelective(TransformerData record);

    TransformerData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(TransformerData record);

    int updateByPrimaryKey(TransformerData record);

    List<TransformerInfoDao> getTransformerInfoList(QueryDeviceInfoDao queryDeviceInfoDao);
}