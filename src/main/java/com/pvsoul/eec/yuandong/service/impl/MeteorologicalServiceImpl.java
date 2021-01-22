package com.pvsoul.eec.yuandong.service.impl;

import com.pvsoul.eec.yuandong.dto.MeteorologicalContentDto;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.entity.MeteorologicalData;
import com.pvsoul.eec.yuandong.mapper.MeteorologicalDataMapper;
import com.pvsoul.eec.yuandong.service.MeteorologicalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class MeteorologicalServiceImpl implements MeteorologicalService {

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
}
