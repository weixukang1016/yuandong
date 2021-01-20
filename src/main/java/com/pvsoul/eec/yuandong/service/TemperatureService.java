package com.pvsoul.eec.yuandong.service;

import com.pvsoul.eec.yuandong.dao.ResultDao;
import com.pvsoul.eec.yuandong.dao.TemperatureContentDao;

public interface TemperatureService {
    public ResultDao SaveData(TemperatureContentDao data);
}
