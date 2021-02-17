package com.pvsoul.eec.yuandong.service;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetDeviceInfoResponseDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetDeviceStatusInfoResponseDto;

import java.util.List;

public interface DeviceService {

    ResultDto getDevicesInfo();

    ResultDto getDevicesStatusInfo(int deviceTypeCode);
}
