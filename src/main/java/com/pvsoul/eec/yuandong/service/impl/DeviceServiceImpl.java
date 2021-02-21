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

    @Autowired
    private TransformerDataMapper transformerDataMapper;

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
            List<DeviceStatusCountDao> devicesStatusCount = pvStringMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.COMBINER_BOX.getDeviceTypeCode()) {
            List<DeviceStatusCountDao> devicesStatusCount = combinerBoxMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.INVERTER.getDeviceTypeCode()) {
            List<DeviceStatusCountDao> devicesStatusCount = inverterMapper.getDeviceStatusCount();
            setDeviceStatusCount(devicesStatusCount, result);
        } else if (deviceTypeCode == DeviceType.TRANSFORMER.getDeviceTypeCode()) {

        } else if (deviceTypeCode == DeviceType.ALL.getDeviceTypeCode()) {
            List<DeviceStatusCountDao> devicesStatusCount = pvStringMapper.getDeviceStatusCount();
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
            pvStringInfoDto.setU(pvStringInfoDao.getU());
            pvStringInfoDto.setI(pvStringInfoDao.getI());
            if (pvStringInfoDao.getI() != null && pvStringInfoDao.getU() != null) {
                pvStringInfoDto.setP(pvStringInfoDao.getI() * pvStringInfoDao.getU());
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
            inverterInfoDto.setI(inverterInfoDao.getI());
            inverterInfoDto.setU(inverterInfoDao.getU());
            inverterInfoDto.setP(inverterInfoDao.getP());
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
            combinerBoxInfoDto.setI(combinerBoxInfoDao.getI());
            combinerBoxInfoDto.setU(combinerBoxInfoDao.getU());
            combinerBoxInfoDto.setP(combinerBoxInfoDao.getP());
            combinerBoxInfoDto.setTemperature(combinerBoxInfoDao.getTemperature());
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
            transformerInfoDto.setLU(transformerInfoDao.getLU());
            transformerInfoDto.setHU(transformerInfoDao.getHU());
            transformerInfoDto.setPac(transformerInfoDao.getPac());
            transformerInfoDto.setFac(transformerInfoDao.getFac());
            transformerInfoDto.setPFactor(transformerInfoDao.getPFactor());
            transformerInfoDto.setInverterCount(transformerInfoDao.getInverterCount());
            transformerInfoDtos.add(transformerInfoDto);
        }
        transformerInfoListReponseDto.setTransformerInfos(transformerInfoDtos);
        resultDto.setEntity(transformerInfoListReponseDto);
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
