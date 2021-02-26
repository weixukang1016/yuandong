package com.pvsoul.eec.yuandong.service.impl;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pvsoul.eec.yuandong.dao.*;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetDeviceListRequestDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetPvstringDetailRequestDto;
import com.pvsoul.eec.yuandong.dto.web.response.*;
import com.pvsoul.eec.yuandong.entity.*;
import com.pvsoul.eec.yuandong.mapper.*;
import com.pvsoul.eec.yuandong.service.DeviceService;
import com.pvsoul.eec.yuandong.util.DeviceStatus;
import com.pvsoul.eec.yuandong.util.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private static final int DATA_VALID_MINITUES = 10; //数据有效时间（10分钟）

    @Autowired
    private TransformerMapper transformerMapper;

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

    @Autowired
    private TransformerDataMapper transformerDataMapper;

    @Autowired
    private PvStringRealModelMapper pvStringRealModelMapper;

    @Override
    public ResultDto getDevicesInfo() {
        ResultDto resultDto = new ResultDto();
        List<GetDeviceInfoResponseDto> result = new ArrayList<GetDeviceInfoResponseDto>();

        QueryWrapper<Transformer> transformerQueryWrapper = new QueryWrapper<>();
        transformerQueryWrapper.eq("is_valid", true);
        int transformerCount = transformerMapper.selectCount(transformerQueryWrapper);
        GetDeviceInfoResponseDto getDeviceInfoResponseDto = new GetDeviceInfoResponseDto();
        getDeviceInfoResponseDto.setDeviceType(DeviceType.TRANSFORMER.getDeviceType());
        getDeviceInfoResponseDto.setDeviceTypeCode(DeviceType.TRANSFORMER.getDeviceTypeCode());
        getDeviceInfoResponseDto.setDeviceCount(transformerCount);
        result.add(getDeviceInfoResponseDto);

        QueryWrapper<Inverter> inverterQueryWrapper = new QueryWrapper<>();
        inverterQueryWrapper.eq("is_valid", true);
        int integerCount = inverterMapper.selectCount(inverterQueryWrapper);
        getDeviceInfoResponseDto = new GetDeviceInfoResponseDto();
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

        //设置全部数据
        int totalCount = transformerCount + integerCount + combinerBoxCount + pvStringCount;
        getDeviceInfoResponseDto = new GetDeviceInfoResponseDto();
        getDeviceInfoResponseDto.setDeviceType(DeviceType.ALL.getDeviceType());
        getDeviceInfoResponseDto.setDeviceTypeCode(DeviceType.ALL.getDeviceTypeCode());
        getDeviceInfoResponseDto.setDeviceCount(totalCount);
        result.add(0, getDeviceInfoResponseDto);

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
            List<DeviceStatusCountDao> devicesStatusCount = pvStringMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.COMBINER_BOX.getDeviceTypeCode()) {
            List<DeviceStatusCountDao> devicesStatusCount = combinerBoxMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.INVERTER.getDeviceTypeCode()) {
            List<DeviceStatusCountDao> devicesStatusCount = inverterMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.TRANSFORMER.getDeviceTypeCode()) {
            List<DeviceStatusCountDao> devicesStatusCount = transformerMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.ALL.getDeviceTypeCode()) {
            List<DeviceStatusCountDao> devicesStatusCount = pvStringMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
            devicesStatusCount = combinerBoxMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
            devicesStatusCount = inverterMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
            devicesStatusCount = transformerMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        }
        //设置全部的设备状态
        int totalDeviceCount = 0;
        for (GetDeviceStatusInfoResponseDto getDeviceStatusInfo : result) {
            totalDeviceCount += getDeviceStatusInfo.getDeviceCount();
        }
        GetDeviceStatusInfoResponseDto totalDeviceStatusInfo = new GetDeviceStatusInfoResponseDto();
        totalDeviceStatusInfo.setDeviceCount(totalDeviceCount);
        totalDeviceStatusInfo.setDeviceStautsCode(null);
        totalDeviceStatusInfo.setDeviceStauts("全部");
        result.add(0, totalDeviceStatusInfo);

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
        List<PvStringInfoDao> pvStringInfoDaos = pvStringDataMapper.getPvStringInfoList(queryDeviceInfoDao);
        PageInfo page = new PageInfo(pvStringInfoDaos);
        GetPvstringInfoListReponseDto getPvstringInfoListReponseDto = new GetPvstringInfoListReponseDto();
        getPvstringInfoListReponseDto.setPageCount(page.getPages());
        getPvstringInfoListReponseDto.setPageNum(page.getPageNum());
        getPvstringInfoListReponseDto.setPageSize(page.getPageSize());
        getPvstringInfoListReponseDto.setTotalCount(page.getTotal());
        List<PvStringInfoDto> pvStringDatas = new ArrayList<>();
        for(PvStringInfoDao pvStringInfoDao : pvStringInfoDaos) {
            PvStringInfoDto pvStringInfoDto = new PvStringInfoDto();
            pvStringInfoDto.setDeviceId(pvStringInfoDao.getId());
            pvStringInfoDto.setDeviceName(pvStringInfoDao.getStringNo());
            pvStringInfoDto.setStandard(pvStringInfoDao.isStandard());
            if (pvStringInfoDao.getU() != null) {
                pvStringInfoDto.setU(String.format("%.1f", pvStringInfoDao.getU()) + "V");
            } else {
                pvStringInfoDto.setU("");
            }
            if (pvStringInfoDao.getI() != null) {
                pvStringInfoDto.setI(String.format("%.1f", pvStringInfoDao.getI()) + "A");
            } else {
                pvStringInfoDto.setI("");
            }
            if (pvStringInfoDao.getI() != null && pvStringInfoDao.getU() != null) {
                pvStringInfoDto.setP(String.format("%.1f", pvStringInfoDao.getI() * pvStringInfoDao.getU()) + "W");
            } else {
                pvStringInfoDto.setP("");
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
        List<InverterInfoDao> inverterInfoDaos = inverterDataMapper.getInverterInfoList(queryDeviceInfoDao);
        PageInfo page = new PageInfo(inverterInfoDaos);
        GetInverterInfoListReponseDto getInverterInfoListReponseDto = new GetInverterInfoListReponseDto();
        getInverterInfoListReponseDto.setPageCount(page.getPages());
        getInverterInfoListReponseDto.setPageNum(page.getPageNum());
        getInverterInfoListReponseDto.setPageSize(page.getPageSize());
        getInverterInfoListReponseDto.setTotalCount(page.getTotal());
        List<InverterInfoDto> inverterDatas = new ArrayList<>();
        for (InverterInfoDao inverterInfoDao : inverterInfoDaos) {
            InverterInfoDto inverterInfoDto = new InverterInfoDto();
            inverterInfoDto.setDeviceId(inverterInfoDao.getId());
            inverterInfoDto.setDeviceName(inverterInfoDao.getInverterNo());
            inverterInfoDto.setStatus(inverterInfoDao.getStatus());
            if (inverterInfoDao.getI() != null) {
                inverterInfoDto.setI(String.format("%.1f", inverterInfoDao.getI()) + "A");
            } else {
                inverterInfoDto.setI("");
            }
            if (inverterInfoDao.getU() != null) {
                inverterInfoDto.setU(String.format("%.1f", inverterInfoDao.getU()) + "V");
            } else {
                inverterInfoDto.setU("");
            }
            if (inverterInfoDao.getP() != null) {
                inverterInfoDto.setP(String.format("%.1f", inverterInfoDao.getP() / 1000) + "kW");
            } else {
                inverterInfoDto.setP("");
            }
            inverterInfoDto.setCombinerBoxCount(inverterInfoDao.getCombinerBoxCount());
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
        List<CombinerBoxInfoDao> combinerBoxeInfos = combinerBoxDataMapper.getCombinerBoxInfoList(queryDeviceInfoDao);
        PageInfo page = new PageInfo(combinerBoxeInfos);

        GetCombinerBoxInfoListReponseDto getCombinerBoxInfoListReponseDto = new GetCombinerBoxInfoListReponseDto();
        getCombinerBoxInfoListReponseDto.setPageCount(page.getPages());
        getCombinerBoxInfoListReponseDto.setPageNum(page.getPageNum());
        getCombinerBoxInfoListReponseDto.setPageSize(page.getPageSize());
        getCombinerBoxInfoListReponseDto.setTotalCount(page.getTotal());
        List<CombinerBoxInfoDto> combinerBoxDatas = new ArrayList<>();

        for (CombinerBoxInfoDao combinerBoxInfoDao : combinerBoxeInfos) {
            CombinerBoxInfoDto combinerBoxInfoDto = new CombinerBoxInfoDto();
            combinerBoxInfoDto.setDeviceId(combinerBoxInfoDao.getId());
            combinerBoxInfoDto.setDeviceName(combinerBoxInfoDao.getBoxNo());
            combinerBoxInfoDto.setStatus(combinerBoxInfoDao.getStatus());
            if (combinerBoxInfoDao.getI() != null) {
                combinerBoxInfoDto.setI(String.format("%.1f", combinerBoxInfoDao.getI()) + "A");
            } else {
                combinerBoxInfoDto.setI("");
            }
            if (combinerBoxInfoDao.getU() != null) {
                combinerBoxInfoDto.setU(String.format("%.1f", combinerBoxInfoDao.getU()) + "V");
            } else {
                combinerBoxInfoDto.setU("");
            }
            if (combinerBoxInfoDao.getP() != null) {
                combinerBoxInfoDto.setP(String.format("%.1f", combinerBoxInfoDao.getP() / 1000) + "kW");
            } else {
                combinerBoxInfoDto.setP("");
            }
            if (combinerBoxInfoDao.getTemperature() != null) {
                combinerBoxInfoDto.setTemperature(String.format("%.1f", combinerBoxInfoDao.getTemperature()) + "℃");
            } else {
                combinerBoxInfoDto.setTemperature("");
            }
            combinerBoxInfoDto.setPvStringCount(combinerBoxInfoDao.getPvStringCount());
            combinerBoxDatas.add(combinerBoxInfoDto);
        }
        getCombinerBoxInfoListReponseDto.setCombinerBoxInfos(combinerBoxDatas);
        resultDto.setEntity(getCombinerBoxInfoListReponseDto);
        return resultDto;
    }

    @Override
    public ResultDto getTransformerInfoList(GetDeviceListRequestDto getDeviceListRequestDto) {
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
        List<TransformerInfoDao> transformerInfoDaos = transformerDataMapper.getTransformerInfoList(queryDeviceInfoDao);
        PageInfo page = new PageInfo(transformerInfoDaos);

        TransformerInfoListReponseDto transformerInfoListReponseDto = new TransformerInfoListReponseDto();
        transformerInfoListReponseDto.setPageCount(page.getPages());
        transformerInfoListReponseDto.setPageNum(page.getPageNum());
        transformerInfoListReponseDto.setPageSize(page.getPageSize());
        transformerInfoListReponseDto.setTotalCount(page.getTotal());
        List<TransformerInfoDto> transformerInfoDtos = new ArrayList<>();

        for (TransformerInfoDao transformerInfoDao : transformerInfoDaos) {
            TransformerInfoDto transformerInfoDto = new TransformerInfoDto();
            transformerInfoDto.setDeviceId(transformerInfoDao.getId());
            transformerInfoDto.setDeviceName(transformerInfoDao.getTransformerNo());
            transformerInfoDto.setStatus(transformerInfoDao.getStatus());
            if (transformerInfoDao.getLU() != null) {
                transformerInfoDto.setLU(String.format("%.1f", transformerInfoDao.getLU()) + "V");
            } else {
                transformerInfoDto.setLU("");
            }
            if (transformerInfoDao.getHU() != null) {
                transformerInfoDto.setHU(String.format("%.1f", transformerInfoDao.getHU()) + "kV");
            } else {
                transformerInfoDto.setHU("");
            }
            if (transformerInfoDao.getPac() != null) {
                transformerInfoDto.setPac(String.format("%.1f", transformerInfoDao.getPac() / 1000) + "kW");
            } else {
                transformerInfoDto.setPac("");
            }
            if (transformerInfoDao.getFac() != null) {
                transformerInfoDto.setFac(transformerInfoDao.getFac() + "Hz");
            } else {
                transformerInfoDto.setFac("");
            }
            if (transformerInfoDao.getPFactor() != null) {
                transformerInfoDto.setPFactor(String.valueOf(transformerInfoDao.getPFactor()));
            } else {
                transformerInfoDto.setPFactor("");
            }
            transformerInfoDto.setInverterCount(transformerInfoDao.getInverterCount());
            transformerInfoDtos.add(transformerInfoDto);
        }
        transformerInfoListReponseDto.setTransformerInfos(transformerInfoDtos);
        resultDto.setEntity(transformerInfoListReponseDto);
        return resultDto;
    }

    @Override
    public ResultDto getPvstringDetail(GetPvstringDetailRequestDto getPvstringDetailRequestDto) {
        ResultDto resultDto = new ResultDto();
        PvString pvString = pvStringMapper.selectByPrimaryKey(getPvstringDetailRequestDto.getDeviceId());
        GetPvStringDetailResponseDto getPvStringDetailResponseDto = new GetPvStringDetailResponseDto();
        getPvStringDetailResponseDto.setDeviceId(pvString.getId().toString());
        getPvStringDetailResponseDto.setDeviceName(pvString.getStringNo());
        getPvStringDetailResponseDto.setStatus(pvString.getStatus());
        getPvStringDetailResponseDto.setStandard(pvString.getIsStandard());

        Date now = new Date();
        Calendar zeroClock = Calendar.getInstance();
        zeroClock.set(Calendar.HOUR_OF_DAY, 0);
        zeroClock.set(Calendar.MINUTE, 0);
        zeroClock.set(Calendar.SECOND, 0);

        QueryWrapper<PvStringData> pvStringDataQueryWrapper = new QueryWrapper<>();
        pvStringDataQueryWrapper.eq("pv_string_id", getPvstringDetailRequestDto.getDeviceId());
        pvStringDataQueryWrapper.ge("device_time", zeroClock.getTime());
        pvStringDataQueryWrapper.le("device_time", now);
        pvStringDataQueryWrapper.orderByAsc("device_time");
        List<PvStringData> pvStringDatas = pvStringDataMapper.selectList(pvStringDataQueryWrapper);
        List<DeviceDataDto> deviceDataDtos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (PvStringData pvStringData : pvStringDatas) {
            DeviceDataDto deviceDataDto = new DeviceDataDto();
            deviceDataDto.setTime(sdf.format(pvStringData.getDeviceTime()));
            deviceDataDto.setI(pvStringData.getI());
            deviceDataDto.setU(pvStringData.getU());
            deviceDataDto.setP(Float.valueOf(String.format("%.1f", pvStringData.getU() * pvStringData.getI())));
            deviceDataDto.setTemperature(Float.valueOf(String.format("%.1f", pvStringData.getTemperature())));
            deviceDataDtos.add(deviceDataDto);
        }
        if (deviceDataDtos.size() > 0) {
            DeviceDataDto lastDeviceData = deviceDataDtos.get(deviceDataDtos.size() -  1);
            getPvStringDetailResponseDto.setI(lastDeviceData.getI().toString() + "A");
            getPvStringDetailResponseDto.setU(lastDeviceData.getU().toString() + "V");
            getPvStringDetailResponseDto.setP(lastDeviceData.getP().toString() + "W");
        } else {
            getPvStringDetailResponseDto.setI("");
            getPvStringDetailResponseDto.setU("");
            getPvStringDetailResponseDto.setP("");
        }

        QueryWrapper<PvStringRealModel> pvStringRealModelQueryWrapper = new QueryWrapper<>();
        pvStringRealModelQueryWrapper.eq("pv_string_id", getPvstringDetailRequestDto.getDeviceId());
        pvStringRealModelQueryWrapper.eq("is_valid",true);
        PvStringRealModel pvStringRealModel = pvStringRealModelMapper.selectOne(pvStringRealModelQueryWrapper);
        if (pvStringRealModel != null) {
            getPvStringDetailResponseDto.setDegradationRatio(String.valueOf(pvStringRealModel.getDegradationRatio() * 100) + "%");
            getPvStringDetailResponseDto.setSoilingRatio(String.valueOf(pvStringRealModel.getSoilingRatio() * 100) + "%");
        } else {
            getPvStringDetailResponseDto.setDegradationRatio("");
            getPvStringDetailResponseDto.setSoilingRatio("");
        }

        getPvStringDetailResponseDto.setDeviceDataOfToday(deviceDataDtos);
        resultDto.setEntity(getPvStringDetailResponseDto);
        return resultDto;
    }

    private void setDeviceStatusCount(List<DeviceStatusCountDao> devicesStatusCount, List<GetDeviceStatusInfoResponseDto> result) {

        for (DeviceStatusCountDao deviceStatusCountDao : devicesStatusCount) {
            for (GetDeviceStatusInfoResponseDto deviceStatusCountDto : result) {
                if (deviceStatusCountDao.getStatus() == deviceStatusCountDto.getDeviceStautsCode()) {
                    int deviceCount = deviceStatusCountDao.getDeviceCount() + deviceStatusCountDto.getDeviceCount();
                    deviceStatusCountDto.setDeviceCount(deviceCount);
                }
            }
        }
    }
}
