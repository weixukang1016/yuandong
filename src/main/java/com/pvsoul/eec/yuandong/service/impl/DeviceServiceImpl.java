package com.pvsoul.eec.yuandong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pvsoul.eec.yuandong.dao.PvStringInfo;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetPvstringListRequestDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetDeviceInfoResponseDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetDeviceStatusInfoResponseDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetPvstringListReponseDto;
import com.pvsoul.eec.yuandong.dto.web.response.PvStringInfoDto;
import com.pvsoul.eec.yuandong.entity.CombinerBox;
import com.pvsoul.eec.yuandong.dao.DeviceStatusCount;
import com.pvsoul.eec.yuandong.entity.Inverter;
import com.pvsoul.eec.yuandong.entity.PvString;
import com.pvsoul.eec.yuandong.mapper.CombinerBoxMapper;
import com.pvsoul.eec.yuandong.mapper.InverterMapper;
import com.pvsoul.eec.yuandong.mapper.PvStringDataMapper;
import com.pvsoul.eec.yuandong.mapper.PvStringMapper;
import com.pvsoul.eec.yuandong.service.DeviceService;
import com.pvsoul.eec.yuandong.util.DeviceStatus;
import com.pvsoul.eec.yuandong.util.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private InverterMapper inverterMapper;

    @Autowired
    private CombinerBoxMapper combinerBoxMapper;

    @Autowired
    private PvStringMapper pvStringMapper;

    @Autowired
    private PvStringDataMapper pvStringDataMapper;

    @Override
    public ResultDto getDevicesInfo() {
        ResultDto resultDto = new ResultDto();
        List<GetDeviceInfoResponseDto> result = new ArrayList<GetDeviceInfoResponseDto>();

        QueryWrapper<Inverter> inverterQueryWrapper = new QueryWrapper<>();
        inverterQueryWrapper.eq("is_valid", true);
        int integerCount = inverterMapper.selectCount(inverterQueryWrapper);
        GetDeviceInfoResponseDto getDeviceInfoResponseDto = new GetDeviceInfoResponseDto();
        getDeviceInfoResponseDto.setDeviceType(DeviceType.INVERTER.getDeviceType());
        getDeviceInfoResponseDto.setDeviceTypeCode(DeviceType.INVERTER.getDeviceTypeCode());
        getDeviceInfoResponseDto.setDeviceCount(integerCount);
        result.add(getDeviceInfoResponseDto);

        QueryWrapper<CombinerBox> combinerBoxQueryWrapper = new QueryWrapper<>();
        combinerBoxQueryWrapper.eq("is_valid", true);
        int combinerBoxCount = combinerBoxMapper.selectCount(combinerBoxQueryWrapper);
        getDeviceInfoResponseDto = new GetDeviceInfoResponseDto();
        getDeviceInfoResponseDto.setDeviceType(DeviceType.COMBINER_BOX.getDeviceType());
        getDeviceInfoResponseDto.setDeviceTypeCode(DeviceType.COMBINER_BOX.getDeviceTypeCode());
        getDeviceInfoResponseDto.setDeviceCount(combinerBoxCount);
        result.add(getDeviceInfoResponseDto);

        QueryWrapper<PvString> pvStringQueryWrapper = new QueryWrapper<>();
        pvStringQueryWrapper.eq("is_valid", true);
        int pvStringCount = pvStringMapper.selectCount(pvStringQueryWrapper);
        getDeviceInfoResponseDto = new GetDeviceInfoResponseDto();
        getDeviceInfoResponseDto.setDeviceType(DeviceType.PVSTRING.getDeviceType());
        getDeviceInfoResponseDto.setDeviceTypeCode(DeviceType.PVSTRING.getDeviceTypeCode());
        getDeviceInfoResponseDto.setDeviceCount(pvStringCount);
        result.add(getDeviceInfoResponseDto);
        resultDto.setEntity(result);
        return resultDto;
    }

    @Override
    public ResultDto getDevicesStatusInfo(int deviceTypeCode) {
        ResultDto resultDto = new ResultDto();
        List<GetDeviceStatusInfoResponseDto> result = new ArrayList<GetDeviceStatusInfoResponseDto>();
        GetDeviceStatusInfoResponseDto getDeviceStatusInfoResponseDto = new GetDeviceStatusInfoResponseDto();

        getDeviceStatusInfoResponseDto.setDeviceStautsCode(DeviceStatus.NORMAL.getDeviceStatusCode());
        getDeviceStatusInfoResponseDto.setDeviceStauts(DeviceStatus.NORMAL.getDeviceStatus());
        getDeviceStatusInfoResponseDto.setDeviceCount(0);
        result.add(getDeviceStatusInfoResponseDto);

        getDeviceStatusInfoResponseDto = new GetDeviceStatusInfoResponseDto();
        getDeviceStatusInfoResponseDto.setDeviceStautsCode(DeviceStatus.FAULT.getDeviceStatusCode());
        getDeviceStatusInfoResponseDto.setDeviceStauts(DeviceStatus.FAULT.getDeviceStatus());
        getDeviceStatusInfoResponseDto.setDeviceCount(0);
        result.add(getDeviceStatusInfoResponseDto);


        getDeviceStatusInfoResponseDto = new GetDeviceStatusInfoResponseDto();
        getDeviceStatusInfoResponseDto.setDeviceStautsCode(DeviceStatus.LOW_EFFICIENCY.getDeviceStatusCode());
        getDeviceStatusInfoResponseDto.setDeviceStauts(DeviceStatus.LOW_EFFICIENCY.getDeviceStatus());
        getDeviceStatusInfoResponseDto.setDeviceCount(0);
        result.add(getDeviceStatusInfoResponseDto);

        if (deviceTypeCode == DeviceType.PVSTRING.getDeviceTypeCode()) {
            List<DeviceStatusCount> devicesStatusCount = pvStringMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.COMBINER_BOX.getDeviceTypeCode()) {
            List<DeviceStatusCount> devicesStatusCount = combinerBoxMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.INVERTER.getDeviceTypeCode()) {
            List<DeviceStatusCount> devicesStatusCount = inverterMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.TRANSFORMER.getDeviceTypeCode()) {

        } else if (deviceTypeCode == DeviceType.ALL.getDeviceTypeCode()) {
            List<DeviceStatusCount> devicesStatusCount = pvStringMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
            devicesStatusCount = combinerBoxMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
            devicesStatusCount = inverterMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        }
        resultDto.setEntity(result);
        return resultDto;
    }

    @Override
    public ResultDto getPvStringInfoList(GetPvstringListRequestDto getPvstringListRequestDto) {
        ResultDto resultDto = new ResultDto();
        PageHelper.startPage(getPvstringListRequestDto.getPageNum(), getPvstringListRequestDto.getPageSize());
        List<PvStringInfo> pvStringInfos = pvStringDataMapper.getPvStringList(getPvstringListRequestDto.getDeviceStatus());
        PageInfo page = new PageInfo(pvStringInfos);
        GetPvstringListReponseDto getPvstringListReponseDto = new GetPvstringListReponseDto();
        getPvstringListReponseDto.setPageCount(page.getPages());
        getPvstringListReponseDto.setPageNum(page.getPageNum());
        getPvstringListReponseDto.setPageSize(page.getPageSize());
        getPvstringListReponseDto.setTotalCount(page.getTotal());
        List<PvStringInfoDto> pvStringDatas = new ArrayList<>();
        for(PvStringInfo pvStringInfo : pvStringInfos) {
            PvStringInfoDto pvStringInfoDto = new PvStringInfoDto();
            pvStringInfoDto.setDeviceId(pvStringInfo.getId());
            pvStringInfoDto.setDeviceName(pvStringInfo.getStringNo());
            pvStringInfoDto.setStandard(pvStringInfo.isStandard());
            pvStringInfoDto.setU(pvStringInfo.getU());
            pvStringInfoDto.setI(pvStringInfo.getI());
            pvStringInfoDto.setP(pvStringInfo.getI() * pvStringInfo.getU());
            pvStringDatas.add(pvStringInfoDto);
        }
        getPvstringListReponseDto.setPvStringDatas(pvStringDatas);
        resultDto.setEntity(getPvstringListReponseDto);
        return resultDto;
    }

    private void setDeviceStatusCount(List<DeviceStatusCount> devicesStatusCount, List<GetDeviceStatusInfoResponseDto> result) {

        for (DeviceStatusCount deviceStatusCount : devicesStatusCount) {
            for (GetDeviceStatusInfoResponseDto deviceStatusCountDto : result) {
                if (deviceStatusCount.getStatus() == deviceStatusCountDto.getDeviceStautsCode()) {
                    int deviceCount = deviceStatusCount.getDeviceCount() + deviceStatusCountDto.getDeviceCount();
                    deviceStatusCountDto.setDeviceCount(deviceCount);
                }
            }
        }
    }
}
