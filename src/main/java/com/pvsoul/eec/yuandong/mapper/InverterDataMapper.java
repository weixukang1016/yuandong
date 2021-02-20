package com.pvsoul.eec.yuandong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pvsoul.eec.yuandong.dao.InverterInfo;
import com.pvsoul.eec.yuandong.dao.QueryDeviceInfoDao;
import com.pvsoul.eec.yuandong.entity.InverterData;

import java.util.Date;
import java.util.List;

public interface InverterDataMapper extends BaseMapper<InverterData> {
    int deleteByPrimaryKey(Object id);

    int insert(InverterData record);

    int insertSelective(InverterData record);

    InverterData selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(InverterData record);

    int updateByPrimaryKey(InverterData record);

    /**
     * 获取截止时间之前的各逆变器最后一次数据
     * @param endTime 截止时间
     * @return
     */
    List<InverterData> getInverterLastDatas(Date endTime);

    /**
     * 根据状态获取逆变器的信息
     * @param queryDeviceInfoDao
     * @return
     */
    List<InverterInfo> getInverterInfoList(QueryDeviceInfoDao queryDeviceInfoDao);
}