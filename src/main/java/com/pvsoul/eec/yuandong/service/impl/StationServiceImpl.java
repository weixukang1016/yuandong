package com.pvsoul.eec.yuandong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetPowerStationInfoDto;
import com.pvsoul.eec.yuandong.dto.web.response.StationInfoDto;
import com.pvsoul.eec.yuandong.entity.Inverter;
import com.pvsoul.eec.yuandong.entity.InverterData;
import com.pvsoul.eec.yuandong.entity.PowerStation;
import com.pvsoul.eec.yuandong.mapper.InverterDataMapper;
import com.pvsoul.eec.yuandong.mapper.PowerStationMapper;
import com.pvsoul.eec.yuandong.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StationServiceImpl implements StationService {

    @Autowired
    PowerStationMapper powerStationMapper;

    @Autowired
    private InverterDataMapper inverterDataMapper;

    @Override
    public ResultDto getPowerStationInfo() {

        ResultDto resultDto = new ResultDto();

        QueryWrapper<PowerStation> powerStationQueryWrapper = new QueryWrapper<>();
        powerStationQueryWrapper.eq("is_valid", true);

        //远东学校项目只有一个电站
        PowerStation powerStation = powerStationMapper.selectOne(powerStationQueryWrapper);
        GetPowerStationInfoDto getPowerStationInfoDto = new GetPowerStationInfoDto();
        StationInfoDto stationInfoDto = new StationInfoDto();
        stationInfoDto.setStationName(powerStation.getStationName());
        stationInfoDto.setStationPhoto(powerStation.getStationPhoto());
        stationInfoDto.setCapacity(powerStation.getCapacity().toString() + "kW");

        Date now = new Date();
        List<InverterData> inverterLastDatas = inverterDataMapper.getInverterLastDatas(now);
        Float powerToday = 0f;
        Float powerMonth = 0f;
        Float powerYear = 0f;
        Float powerTotal = 0f;
        for(InverterData inverterLastData : inverterLastDatas) {
            if (now.getYear() == inverterLastData.getDeviceTime().getYear()) {
                powerYear += inverterLastData.geteYear();
                if (now.getMonth() == inverterLastData.getDeviceTime().getMonth()) {
                    powerMonth += inverterLastData.geteMonth();
                    if (now.getDay() == inverterLastData.getDeviceTime().getDay()) {
                        powerToday += inverterLastData.geteToday();
                    }
                }
            }
            powerTotal += inverterLastData.geteTotal();
        }
        stationInfoDto.setPowerToday(powerToday.toString() + "kWh");
        stationInfoDto.setPowerThisMonth(powerMonth.toString() + "kWh");
        stationInfoDto.setPowerThisYear(powerYear.toString() + "kWh");
        stationInfoDto.setPowerTotal(powerTotal.toString() + "kWh");

        getPowerStationInfoDto.setStationInfoDto(stationInfoDto);
        resultDto.setEntity(getPowerStationInfoDto);
        return resultDto;
    }
}
