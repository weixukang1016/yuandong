package com.pvsoul.eec.yuandong.service;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetDeviceListRequestDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetPvstringDetailRequestDto;

public interface DeviceService {

    ResultDto getDevicesInfo();

    ResultDto getDevicesStatusInfo(int deviceTypeCode);

    ResultDto getPvStringInfoList(GetDeviceListRequestDto getDeviceListRequestDto);

    ResultDto getInverterInfoList(GetDeviceListRequestDto getDeviceListRequestDto);

    ResultDto getCombinerBoxInfoList(GetDeviceListRequestDto getDeviceListRequestDto);

    ResultDto getTransformerInfoList(GetDeviceListRequestDto getDeviceListRequestDto);

    ResultDto getPvstringDetail(GetPvstringDetailRequestDto deviceId);
}
