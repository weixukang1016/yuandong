package com.pvsoul.eec.yuandong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pvsoul.eec.yuandong.dao.*;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetDeviceListRequestDto;
import com.pvsoul.eec.yuandong.dto.web.response.*;
import com.pvsoul.eec.yuandong.entity.CombinerBox;
import com.pvsoul.eec.yuandong.entity.Inverter;
import com.pvsoul.eec.yuandong.entity.PvString;
import com.pvsoul.eec.yuandong.mapper.*;
import com.pvsoul.eec.yuandong.service.DeviceService;
import com.pvsoul.eec.yuandong.util.DeviceStatus;
import com.pvsoul.eec.yuandong.util.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private static final int DATA_VALID_MINITUES = 10; //数据有效时间（10分钟）

    @Autowired
    private InverterMapper inverterMapper;

    @Autowired
    private CombinerBoxMapper combinerBoxMapper;

    @Autowired
    private PvStringMapper pvStringMapper;

    @Autowired
    private PvStringDataMapper pvStringDataMapper;

    @Autowired
    private CombinerBoxDataMapper combinerBoxDataMapper;

    @Autowired
    private InverterDataMapper inverterDataMapper;

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
    public ResultDto getPvStringInfoList(GetDeviceListRequestDto getDeviceListRequestDto) {
        ResultDto resultDto = new ResultDto();
        Date now = new Date();
        Calendar before10M = Calendar.getInstance();
        before10M.setTime(now);
        before10M.add(Calendar.MINUTE, -DATA_VALID_MINITUES);

        QueryDeviceInfoDao queryDeviceInfoDao = new QueryDeviceInfoDao();
        queryDeviceInfoDao.setDeviceStatus(getDeviceListRequestDto.getDeviceStatus());
        queryDeviceInfoDao.setStartTime(before10M.getTime());
        queryDeviceInfoDao.setEndTime(now);

        PageHelper.startPage(getDeviceListRequestDto.getPageNum(), getDeviceListRequestDto.getPageSize());
        List<PvStringInfo> pvStringInfos = pvStringDataMapper.getPvStringInfoList(queryDeviceInfoDao);
        PageInfo page = new PageInfo(pvStringInfos);
        GetPvstringInfoListReponseDto getPvstringInfoListReponseDto = new GetPvstringInfoListReponseDto();
        getPvstringInfoListReponseDto.setPageCount(page.getPages());
        getPvstringInfoListReponseDto.setPageNum(page.getPageNum());
        getPvstringInfoListReponseDto.setPageSize(page.getPageSize());
        getPvstringInfoListReponseDto.setTotalCount(page.getTotal());
        List<PvStringInfoDto> pvStringDatas = new ArrayList<>();
        for(PvStringInfo pvStringInfo : pvStringInfos) {
            PvStringInfoDto pvStringInfoDto = new PvStringInfoDto();
            pvStringInfoDto.setDeviceId(pvStringInfo.getId());
            pvStringInfoDto.setDeviceName(pvStringInfo.getStringNo());
            pvStringInfoDto.setStandard(pvStringInfo.isStandard());
            pvStringInfoDto.setU(pvStringInfo.getU());
            pvStringInfoDto.setI(pvStringInfo.getI());
            if (pvStringInfo.getI() != null && pvStringInfo.getU() != null) {
                pvStringInfoDto.setP(pvStringInfo.getI() * pvStringInfo.getU());
            }
            pvStringDatas.add(pvStringInfoDto);
        }
        getPvstringInfoListReponseDto.setPvStringInfos(pvStringDatas);
        resultDto.setEntity(getPvstringInfoListReponseDto);
        return resultDto;
    }

    @Override
    public ResultDto getInverterInfoList(GetDeviceListRequestDto getDeviceListRequestDto) {
        ResultDto resultDto = new ResultDto();
        Date now = new Date();
        Calendar before10M = Calendar.getInstance();
        before10M.setTime(now);
        before10M.add(Calendar.MINUTE, -DATA_VALID_MINITUES);

        QueryDeviceInfoDao queryDeviceInfoDao = new QueryDeviceInfoDao();
        queryDeviceInfoDao.setDeviceStatus(getDeviceListRequestDto.getDeviceStatus());
        queryDeviceInfoDao.setStartTime(before10M.getTime());
        queryDeviceInfoDao.setEndTime(now);

        PageHelper.startPage(getDeviceListRequestDto.getPageNum(), getDeviceListRequestDto.getPageSize());
        List<InverterInfo> inverterInfos = inverterDataMapper.getInverterInfoList(queryDeviceInfoDao);
        PageInfo page = new PageInfo(inverterInfos);
        GetInverterInfoListReponseDto getInverterInfoListReponseDto = new GetInverterInfoListReponseDto();
        getInverterInfoListReponseDto.setPageCount(page.getPages());
        getInverterInfoListReponseDto.setPageNum(page.getPageNum());
        getInverterInfoListReponseDto.setPageSize(page.getPageSize());
        getInverterInfoListReponseDto.setTotalCount(page.getTotal());
        List<InverterInfoDto> inverterDatas = new ArrayList<>();
        for (InverterInfo inverterInfo : inverterInfos) {
            InverterInfoDto inverterInfoDto = new InverterInfoDto();
            inverterInfoDto.setDeviceId(inverterInfo.getId());
            inverterInfoDto.setDeviceName(inverterInfo.getInverterNo());
            inverterInfoDto.setStatus(inverterInfo.getStatus());
            inverterInfoDto.setI(inverterInfo.getI());
            inverterInfoDto.setU(inverterInfo.getU());
            inverterInfoDto.setP(inverterInfo.getP());
            inverterInfoDto.setCombinerBoxCount(inverterInfo.getCombinerBoxCount());
            inverterDatas.add(inverterInfoDto);
        }
        getInverterInfoListReponseDto.setInverterInfos(inverterDatas);
        resultDto.setEntity(getInverterInfoListReponseDto);
        return resultDto;
    }

    @Override
    public ResultDto getCombinerBoxInfoList(GetDeviceListRequestDto getDeviceListRequestDto) {
        ResultDto resultDto = new ResultDto();

        Date now = new Date();
        Calendar before10M = Calendar.getInstance();
        before10M.setTime(now);
        before10M.add(Calendar.MINUTE, -DATA_VALID_MINITUES);

        QueryDeviceInfoDao queryDeviceInfoDao = new QueryDeviceInfoDao();
        queryDeviceInfoDao.setDeviceStatus(getDeviceListRequestDto.getDeviceStatus());
        queryDeviceInfoDao.setStartTime(before10M.getTime());
        queryDeviceInfoDao.setEndTime(now);

        PageHelper.startPage(getDeviceListRequestDto.getPageNum(), getDeviceListRequestDto.getPageSize());
        List<CombinerBoxInfo> combinerBoxeInfos = combinerBoxDataMapper.getCombinerBoxInfoList(queryDeviceInfoDao);
        PageInfo page = new PageInfo(combinerBoxeInfos);

        GetCombinerBoxInfoListReponseDto getCombinerBoxInfoListReponseDto = new GetCombinerBoxInfoListReponseDto();
        getCombinerBoxInfoListReponseDto.setPageCount(page.getPages());
        getCombinerBoxInfoListReponseDto.setPageNum(page.getPageNum());
        getCombinerBoxInfoListReponseDto.setPageSize(page.getPageSize());
        getCombinerBoxInfoListReponseDto.setTotalCount(page.getTotal());
        List<CombinerBoxInfoDto> combinerBoxDatas = new ArrayList<>();

        for (CombinerBoxInfo combinerBoxInfo : combinerBoxeInfos) {
            CombinerBoxInfoDto combinerBoxInfoDto = new CombinerBoxInfoDto();
            combinerBoxInfoDto.setDeviceId(combinerBoxInfo.getId());
            combinerBoxInfoDto.setDeviceName(combinerBoxInfo.getBoxNo());
            combinerBoxInfoDto.setStatus(combinerBoxInfo.getStatus());
            combinerBoxInfoDto.setI(combinerBoxInfo.getI());
            combinerBoxInfoDto.setU(combinerBoxInfo.getU());
            combinerBoxInfoDto.setP(combinerBoxInfo.getP());
            combinerBoxInfoDto.setPvStringCount(combinerBoxInfo.getPvStringCount());
            combinerBoxDatas.add(combinerBoxInfoDto);
        }
        getCombinerBoxInfoListReponseDto.setCombinerBoxInfos(combinerBoxDatas);
        resultDto.setEntity(getCombinerBoxInfoListReponseDto);
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
