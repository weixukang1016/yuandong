package com.pvsoul.eec.yuandong.service;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.devicedata.TemperatureContentDto;

public interface TemperatureService {
    public ResultDto SaveData(TemperatureContentDto data);
}
