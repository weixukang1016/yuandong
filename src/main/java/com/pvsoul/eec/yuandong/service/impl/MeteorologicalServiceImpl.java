package com.pvsoul.eec.yuandong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pvsoul.eec.yuandong.dto.devicedata.MeteorologicalContentDto;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetMeteoDataResponseDto;
import com.pvsoul.eec.yuandong.entity.MeteorologicalData;
import com.pvsoul.eec.yuandong.mapper.MeteorologicalDataMapper;
import com.pvsoul.eec.yuandong.service.MeteorologicalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MeteorologicalServiceImpl implements MeteorologicalService {

    private static final int DATA_VALID_MINITUES = 10; //数据有效时间（10分钟）

    @Autowired
    private MeteorologicalDataMapper meteorologicalDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto SaveData(MeteorologicalContentDto content) {
        ResultDto resultDto = new ResultDto();
        Date now = new Date();
        String id = UUID.randomUUID().toString();
        MeteorologicalData meteorologicalData = new MeteorologicalData();
        meteorologicalData.setId(id);
        meteorologicalData.setDeviceId(content.getDeviceId());
        meteorologicalData.setTemperature(content.getTemperature());
        meteorologicalData.setHumidity(content.getHumidity());
        meteorologicalData.setRainFall(content.getRainFall());
        meteorologicalData.setWindDirection(content.getWindDirection());
        meteorologicalData.setWindSpeed(content.getWindSpeed());
        meteorologicalData.setPressure(content.getPressure());
        meteorologicalData.setIrradiance(content.getIrradiance());
        meteorologicalData.setCreateTime(now);
        meteorologicalDataMapper.insert(meteorologicalData);

        return resultDto;
    }

    @Override
    public ResultDto GetMeteoData() {

        ResultDto resultDto = new ResultDto();
        Calendar before10M = Calendar.getInstance();
        before10M.add(Calendar.MINUTE, -DATA_VALID_MINITUES);

        //调试代码
        //before10M.add(Calendar.YEAR, -1);

        QueryWrapper<MeteorologicalData> meteorologicalDataQueryWrapper = new QueryWrapper<MeteorologicalData>();
        meteorologicalDataQueryWrapper.ge("create_time", before10M.getTime()).orderByDesc("create_time").last("limit 1");
        MeteorologicalData meteorologicalData = meteorologicalDataMapper.selectOne(meteorologicalDataQueryWrapper);
        GetMeteoDataResponseDto getMeteoDataResponseDto = new GetMeteoDataResponseDto();
        getMeteoDataResponseDto.setTemperature(meteorologicalData.getTemperature());
        getMeteoDataResponseDto.setHumidity(meteorologicalData.getHumidity());
        getMeteoDataResponseDto.setWindDirection(getWindDirection(meteorologicalData.getWindDirection()));
        getMeteoDataResponseDto.setWindSpeed(meteorologicalData.getWindSpeed());
        getMeteoDataResponseDto.setPressure(meteorologicalData.getPressure());
        getMeteoDataResponseDto.setPoa(meteorologicalData.getIrradiance());
        resultDto.setEntity(getMeteoDataResponseDto);
        return resultDto;
    }

    private String getWindDirection(Integer windDirection) {
        switch (windDirection) {
            case 0:
                return "北风";
            case 1:
            case 2:
            case 3:
                return "东北风";
            case 4:
                return "东风";
            case 5:
            case 6:
            case 7:
                return "东南风";
            case 8:
                return "南风";
            case 9:
            case 10:
            case 11:
                return "西南风";
            case 12:
                return "西风";
            case 13:
            case 14:
            case 15:
                return "西北风";
            case 16:
                return "北风";
            default:
                return null;
        }
    }
}
