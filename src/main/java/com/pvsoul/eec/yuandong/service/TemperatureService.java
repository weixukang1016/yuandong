package com.pvsoul.eec.yuandong.service;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.TemperatureContentDto;

public interface TemperatureService {
    public ResultDto SaveData(TemperatureContentDto data);
}
