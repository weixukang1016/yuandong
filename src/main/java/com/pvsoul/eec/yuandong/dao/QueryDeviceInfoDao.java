package com.pvsoul.eec.yuandong.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QueryDeviceInfoDao {

    private Integer deviceStatus;

    private Date startTime;

    private Date endTime;
}
