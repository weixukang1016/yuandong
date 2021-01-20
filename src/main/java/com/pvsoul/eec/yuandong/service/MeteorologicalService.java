package com.pvsoul.eec.yuandong.service;

import com.pvsoul.eec.yuandong.dao.MeteorologicalContentDao;
import com.pvsoul.eec.yuandong.dao.ResultDao;

public interface MeteorologicalService {

    public ResultDao SaveData(MeteorologicalContentDao data);
}
