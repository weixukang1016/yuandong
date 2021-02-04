package com.pvsoul.eec.yuandong.service;

import com.pvsoul.eec.yuandong.dto.devicedata.MeteorologicalContentDto;
import com.pvsoul.eec.yuandong.dto.ResultDto;

public interface MeteorologicalService {

    public ResultDto SaveData(MeteorologicalContentDto data);

    public ResultDto GetMeteoData();
}
