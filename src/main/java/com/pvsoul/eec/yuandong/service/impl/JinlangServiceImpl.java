package com.pvsoul.eec.yuandong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pvsoul.eec.yuandong.dto.InverterDataDto;
import com.pvsoul.eec.yuandong.dto.JinLangDataDto;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.UIDataDto;
import com.pvsoul.eec.yuandong.entity.*;
import com.pvsoul.eec.yuandong.mapper.*;
import com.pvsoul.eec.yuandong.service.JinlangService;
import com.pvsoul.eec.yuandong.vo.GetPvStringTemperatureRequestVo;
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
public class JinlangServiceImpl implements JinlangService {

    @Autowired
    private CollectRecordMapper collectRecordMapper;

    @Autowired
    private InverterDataMapper inverterDataMapper;

    @Autowired
    private InverterPvDataMapper inverterPvDataMapper;

    @Autowired
    private InverterAcDataMapper inverterAcDataMapper;

    @Autowired
    private InverterMapper inverterMapper;

    @Autowired
    private PvStringMapper pvStringMapper;

    @Autowired
    private PvStringDataMapper pvStringDataMapper;

    @Autowired
    private TemperatureDataMapper temperatureDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto SaveData(JinLangDataDto data) {
        ResultDto resultDto = new ResultDto();
        Date now = new Date();
        String collectRecordId = UUID.randomUUID().toString();

        CollectRecord collectRecord = new CollectRecord();
        collectRecord.setId(collectRecordId);
        collectRecord.setCollectTime(data.getTime());
        collectRecord.setSn(data.getSn());
        collectRecord.setIsRealtime(data.getIsRealtime());

        collectRecord.setCollectorVer(data.getCollector1().getCollectorVer());
        collectRecord.setCollectorType(data.getCollector1().getType());
        collectRecord.setRssiLevel(data.getCollector1().getRssiLevel());
        collectRecord.setRssi(data.getCollector1().getRssi());
        collectRecord.setCreateTime(now);
        collectRecordMapper.insert(collectRecord);

        for (InverterDataDto inverterDataDao:data.getInverter1()) {

            String inverterDataId = UUID.randomUUID().toString();
            InverterData inverterData = new InverterData();
            inverterData.setId(inverterDataId);
            inverterData.setCollectRecordId(collectRecordId);
            inverterData.setInverterSn(inverterDataDao.getInverterSn());
            inverterData.setInverterModel(inverterDataDao.getInverterModel());
            inverterData.setRatedPower(inverterDataDao.getRatedPower());
            inverterData.setInverterVer(inverterDataDao.getInverterVer());
            inverterData.seteToday(inverterDataDao.getEToday());
            inverterData.seteMonth(inverterDataDao.getEMonth());
            inverterData.seteYear(inverterDataDao.getEYear());
            inverterData.seteTotal(inverterDataDao.getETotal());
            inverterData.setInverterTemp(inverterDataDao.getInverterTemp());
            inverterData.setState(inverterDataDao.getState());
            inverterData.setAlarmCn(inverterDataDao.getAlarmCn());
            inverterData.setAlarmEn(inverterDataDao.getAlarmEn());

            inverterData.setFac(inverterDataDao.getFac());
            inverterData.setPac(inverterDataDao.getPac());
            inverterData.setNationalCode(inverterDataDao.getNationalCode());
            inverterData.setNational(inverterDataDao.getNational());
            inverterData.setCreateTime(now);

            inverterDataMapper.insert(inverterData);

            //查找数据对应的逆变器
            QueryWrapper<Inverter> inverterQueryWrapper = new QueryWrapper<>();
            inverterQueryWrapper.eq("inverter_sn", inverterDataDao.getInverterSn())
                    .eq("is_valid", true);
            Inverter inverter = inverterMapper.selectOne(inverterQueryWrapper);

            int pvIndex = 0;

            for (UIDataDto pvUI:inverterDataDao.getPv()) {
                String inverterPvDataId = UUID.randomUUID().toString();
                InverterPvData inverterPvData = new InverterPvData();
                inverterPvData.setId(inverterPvDataId);
                inverterPvData.setCollectRecordId(collectRecordId);
                inverterPvData.setInverterDataId(inverterDataId);
                inverterPvData.setU(pvUI.getU());
                inverterPvData.setI(pvUI.getI());
                inverterPvData.setCreateTime(now);
                inverterPvData.setPvIndex(pvIndex++);
                inverterPvDataMapper.insert(inverterPvData);

                if (inverter != null) {
                    //查找数据对应的光伏组串
                    QueryWrapper<PvString> pvStringQueryWrapper = new QueryWrapper<>();
                    pvStringQueryWrapper.eq("inverter_id", inverter.getId())
                            .eq("pv_index", pvIndex)
                            .eq("is_valid", true);
                    PvString pvString = pvStringMapper.selectOne(pvStringQueryWrapper);

                    if (pvString != null) {
                        //存在光伏组串时，插入数据
                        PvStringData pvStringData = new PvStringData();
                        pvStringData.setId(UUID.randomUUID());
                        pvStringData.setPowerStationId(inverter.getPowerStationId());
                        pvStringData.setInverterId(inverter.getId());
                        pvStringData.setPvStringId(pvString.getId());
                        pvStringData.setCreateTime(now);
                        pvStringData.setU(pvUI.getU());
                        pvStringData.setI(pvUI.getI());

                        //查询十分钟内最近时间的温度数据的平均值，用于该光伏组串的温度值
                        Calendar before10M = Calendar.getInstance();
                        before10M.setTime(now);
                        before10M.add(Calendar.MINUTE, -10);

                        before10M.add(Calendar.DATE, -2);

                        GetPvStringTemperatureRequestVo getPvStringTemperatureRequestVo = new GetPvStringTemperatureRequestVo();
                        getPvStringTemperatureRequestVo.setInverterId(inverter.getId().toString());
                        getPvStringTemperatureRequestVo.setPvStringId(pvString.getId().toString());
                        getPvStringTemperatureRequestVo.setStartTime(before10M.getTime());
                        getPvStringTemperatureRequestVo.setEndTime(now);
                        List<TemperatureData> pvStringTemperatrue = temperatureDataMapper.getPvStringTemperature(getPvStringTemperatureRequestVo);
                        //pvStringData.setTemperature(pvStringTemperatrue);
                        pvStringDataMapper.insert(pvStringData);
                    }
                }
            }
            int acIndex = 0;
            for (UIDataDto acUI:inverterDataDao.getAc()) {
                String inverterAcDataId = UUID.randomUUID().toString();
                InverterAcData inverterAcData = new InverterAcData();
                inverterAcData.setId(inverterAcDataId);
                inverterAcData.setCollectRecordId(collectRecordId);
                inverterAcData.setInverterDataId(inverterDataId);
                inverterAcData.setU(acUI.getU());
                inverterAcData.setI(acUI.getI());
                inverterAcData.setCreateTime(now);
                inverterAcData.setAcIndex(acIndex++);
                inverterAcDataMapper.insert(inverterAcData);
            }
        }

        return resultDto;
    }
}
