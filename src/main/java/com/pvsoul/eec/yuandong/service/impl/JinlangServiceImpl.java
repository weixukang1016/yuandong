package com.pvsoul.eec.yuandong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pvsoul.eec.yuandong.dto.devicedata.InverterDataDto;
import com.pvsoul.eec.yuandong.dto.devicedata.JinLangDataDto;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.devicedata.UIDataDto;
import com.pvsoul.eec.yuandong.entity.*;
import com.pvsoul.eec.yuandong.mapper.*;
import com.pvsoul.eec.yuandong.service.JinlangService;
import com.pvsoul.eec.yuandong.vo.GetPvStringTemperatureRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class JinlangServiceImpl implements JinlangService {

    private static float TEMPERATURE_DIFF_THRESHOLD = 5; //温度差值的阈值

    private static final int DATA_VALID_MINITUES = 10; //数据有效时间（10分钟）

    private static final float TRANSFORMER_POWER_FACTOR = 0.95f; //功率因数固定为0.95

    @Autowired
    private CollectRecordMapper collectRecordMapper;

    @Autowired
    private TransformerMapper transformerMapper;

    @Autowired
    private InverterDataMapper inverterDataMapper;

    @Autowired
    private InverterPvDataMapper inverterPvDataMapper;

    @Autowired
    private InverterAcDataMapper inverterAcDataMapper;

    @Autowired
    private TransformerDataMapper transformerDataMapper;

    @Autowired
    private InverterMapper inverterMapper;

    @Autowired
    private CombinerBoxMapper combinerBoxMapper;

    @Autowired
    private CombinerBoxDataMapper combinerBoxDataMapper;

    @Autowired
    private PvStringMapper pvStringMapper;

    @Autowired
    private PvStringDataMapper pvStringDataMapper;

    @Autowired
    private TemperatureDataMapper temperatureDataMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto SaveData(JinLangDataDto data) {
        log.info("开始处理锦浪数据");
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

        for (InverterDataDto inverterDataDto:data.getInverter1()) {

            //查找数据对应的逆变器
            QueryWrapper<Inverter> inverterQueryWrapper = new QueryWrapper<>();
            inverterQueryWrapper.eq("inverter_sn", inverterDataDto.getInverterSn())
                    .eq("is_valid", true);
            Inverter inverter = inverterMapper.selectOne(inverterQueryWrapper);
            if (inverter.getIsForTransformer()) {
                //如果是用于采集升压变的逆变器，写入升压变数据
                insertTransformerData(inverter, inverterDataDto, data.getTime(), now);
            }

            String inverterDataId = UUID.randomUUID().toString();
            InverterData inverterData = new InverterData();
            inverterData.setId(inverterDataId);
            inverterData.setCollectRecordId(collectRecordId);
            inverterData.setInverterSn(inverterDataDto.getInverterSn());
            inverterData.setInverterModel(inverterDataDto.getInverterModel());
            inverterData.setRatedPower(inverterDataDto.getRatedPower());
            inverterData.setInverterVer(inverterDataDto.getInverterVer());
            inverterData.seteToday(inverterDataDto.getEToday());
            inverterData.seteMonth(inverterDataDto.getEMonth());
            inverterData.seteYear(inverterDataDto.getEYear());
            inverterData.seteTotal(inverterDataDto.getETotal());
            inverterData.setInverterTemp(inverterDataDto.getInverterTemp());
            inverterData.setState(Integer.parseInt(inverterDataDto.getState()));
            inverterData.setAlarmCn(inverterDataDto.getAlarmCn());
            inverterData.setAlarmEn(inverterDataDto.getAlarmEn());

            inverterData.setFac(inverterDataDto.getFac());
            inverterData.setPac(inverterDataDto.getPac());
            inverterData.setNationalCode(inverterDataDto.getNationalCode());
            inverterData.setNational(inverterDataDto.getNational());
            inverterData.setCreateTime(now);
            inverterData.setDeviceTime(data.getTime());
            inverterData.setPowerStationId(inverter.getPowerStationId());
            inverterData.setTransformerId(inverter.getTransformerId());
            inverterData.setInverterId(inverter.getId());

            inverterDataMapper.insert(inverterData);

            CombinerBox combinerBox = null;
            float combinerI = 0;
            float combinerU = 0;
            float combinerP = 0;
            int pvStringCountInCombiner = 0;
            if (inverter != null) {
                //查找数据对应的汇流箱,因为远东的是光伏组串直接接逆变器，汇流箱是虚拟汇流箱，因此汇流箱是根据逆变器来查找的
                QueryWrapper<CombinerBox> combinerBoxQueryWrapper = new QueryWrapper<>();
                combinerBoxQueryWrapper.eq("inverter_id", inverter.getId())
                        .eq("is_valid", true);
                combinerBox = combinerBoxMapper.selectOne(combinerBoxQueryWrapper);
            }

            int pvIndex = 0;

            for (UIDataDto pvUI:inverterDataDto.getPv()) {
                String inverterPvDataId = UUID.randomUUID().toString();
                InverterPvData inverterPvData = new InverterPvData();
                inverterPvData.setId(inverterPvDataId);
                inverterPvData.setCollectRecordId(collectRecordId);
                inverterPvData.setInverterDataId(inverterDataId);
                inverterPvData.setU(pvUI.getU());
                inverterPvData.setI(pvUI.getI());
                inverterPvData.setCreateTime(now);
                inverterPvData.setPvIndex(pvIndex);
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
                        pvStringData.setPowerStationId(pvString.getPowerStationId());
                        pvStringData.setTransformerId(pvString.getTransformerId());
                        pvStringData.setInverterId(pvString.getInverterId());
                        pvStringData.setCombinerBoxId(pvString.getCombinerBoxId());
                        pvStringData.setPvStringId(pvString.getId());
                        pvStringData.setCreateTime(now);
                        pvStringData.setDeviceTime(data.getTime());
                        pvStringData.setU(pvUI.getU());
                        pvStringData.setI(pvUI.getI());

                        //查询十分钟内最近时间的温度数据的平均值，用于该光伏组串的温度值
                        Calendar before10M = Calendar.getInstance();
                        before10M.setTime(data.getTime());
                        before10M.add(Calendar.MINUTE, -DATA_VALID_MINITUES);

                        //TODO delete 调试代码
                        //before10M.add(Calendar.DATE, -5);

                        GetPvStringTemperatureRequestVo getPvStringTemperatureRequestVo = new GetPvStringTemperatureRequestVo();
                        getPvStringTemperatureRequestVo.setInverterId(inverter.getId().toString());
                        getPvStringTemperatureRequestVo.setPvStringId(pvString.getId().toString());
                        getPvStringTemperatureRequestVo.setStartTime(before10M.getTime());
                        getPvStringTemperatureRequestVo.setEndTime(data.getTime());
                        List<TemperatureData> pvStringTemperatrues = temperatureDataMapper.getPvStringTemperature(getPvStringTemperatureRequestVo);
                        if (pvStringTemperatrues.size() > 0) {
                            Float temperature =  calculatePvStringTemperature(pvStringTemperatrues);
                            pvStringData.setTemperature(temperature);
                        }
                        //pvStringData.setTemperature(pvStringTemperatrue);
                        pvStringDataMapper.insert(pvStringData);
                        if (combinerBox != null) {
                            combinerU += pvUI.getU();
                            combinerI += pvUI.getI();
                            combinerP += (pvUI.getU() * pvUI.getI());
                            pvStringCountInCombiner++;
                        }
                    }
                }
                pvIndex++;
            }

            if (combinerBox != null && pvStringCountInCombiner != 0) {
                CombinerBoxData combinerBoxData = new CombinerBoxData();
                combinerBoxData.setId(UUID.randomUUID().toString());
                combinerBoxData.setPowerStationId(combinerBox.getPowerStationId());
                combinerBoxData.setTransformerId(combinerBox.getTransformerId());
                combinerBoxData.setInverterId(combinerBox.getInverterId());
                combinerBoxData.setCombinerBoxId(combinerBox.getId());
                combinerBoxData.setU(combinerU / pvStringCountInCombiner);  //用逆变器各支路的电压求平均
                combinerBoxData.setI(combinerI); //用逆变器各支路的电流和
                combinerBoxData.setP(combinerP); //用逆变器各支路的电压*电流求和
                combinerBoxData.setTemperature(inverterDataDto.getInverterTemp()); //用逆变器的温度
                combinerBoxData.setCreateTime(now);
                combinerBoxData.setDeviceTime(data.getTime());//用逆变器数据上报的时间
                combinerBoxDataMapper.insert(combinerBoxData);
            }

            int acIndex = 0;
            for (UIDataDto acUI:inverterDataDto.getAc()) {
                String inverterAcDataId = UUID.randomUUID().toString();
                InverterAcData inverterAcData = new InverterAcData();
                inverterAcData.setId(inverterAcDataId);
                inverterAcData.setCollectRecordId(collectRecordId);
                inverterAcData.setInverterDataId(inverterDataId);
                inverterAcData.setU(acUI.getU());
                inverterAcData.setI(acUI.getI());
                inverterAcData.setCreateTime(now);
                inverterAcData.setAcIndex(acIndex);
                inverterAcDataMapper.insert(inverterAcData);
                acIndex++;
            }
            //updateDeviceStatus(inverterDataDto);
        }

        log.info("完成锦浪数据的处理");
        return resultDto;
    }

    /**
     * 根据锦浪逆变器的设备状态码来更新设备状态，如果有故障，记录故障
     * @param inverterDataDto
     */
    private void updateDeviceStatus(InverterDataDto inverterDataDto) {
        Integer code = Integer.parseInt(inverterDataDto.getState());

        if (code != 0) {
            //状态代码不为0时，有设备故障

        } else {

        }

    }

    /**
     * 因为远东学校的数据没有直接采集于升压变，用逆变器的数据来生成升压变的数据
     * @param inverter
     * @param inverterDataDto
     * @param deviceTime
     * @param now
     */
    private void insertTransformerData(Inverter inverter, InverterDataDto inverterDataDto, Date deviceTime, Date now) {
        TransformerData transformerData = new TransformerData();
        transformerData.setId(UUID.randomUUID().toString());
        transformerData.setPowerStationId(inverter.getPowerStationId());
        transformerData.setTransformerId(inverter.getTransformerId());
        //低压侧电压=逆变器AC侧电压3相平均值*1.732  (V)
        //高压侧电压=逆变器AC侧电压3相平均值/220*10 (kV)
        float acU = 0;
        int acCount = 0;
        for (UIDataDto acUI:inverterDataDto.getAc()) {
            acU += acUI.getU();
            acCount++;
        }
        if (acCount != 0) {
            transformerData.setlU((float)((acU / acCount) * 1.732));
            transformerData.sethU((float)((acU / acCount) / 220 *10));
        }
        //频率=逆变器AC侧频率 (50Hz左右波动)
        transformerData.setFac(inverterDataDto.getFac());

        //功率=5个逆变器AC侧有功功率瞬时值之和
        //查询十分钟内最近时间的其他逆变器的功率，用于计算功率之和
        Calendar before10M = Calendar.getInstance();
        before10M.setTime(deviceTime);
        before10M.add(Calendar.MINUTE, -DATA_VALID_MINITUES);

        //TODO delete 调试代码
        //before10M.add(Calendar.DATE, -2);

        QueryWrapper<InverterData> inverterDataQueryWrapper = new QueryWrapper<>();
        inverterDataQueryWrapper.gt("device_time", before10M.getTime())
                .le("device_time", deviceTime)
                .orderByDesc("device_time");
        List<InverterData> inverterDatas = inverterDataMapper.selectList(inverterDataQueryWrapper);

        float total_pac = inverterDataDto.getPac();
        Map<String,Float> inverterDataMap = new HashMap<>();
        for (InverterData inverterData : inverterDatas) {
            if (!inverterData.getInverterId().equals(inverter.getId())) {
                if (!inverterDataMap.containsKey(inverterData.getInverterId().toString())) {
                    inverterDataMap.put(inverterData.getInverterId().toString(), inverterData.getPac());
                    total_pac += inverterData.getPac();
                }
            }
        }
        transformerData.setPac(total_pac);

        //功率因数=0.95
        transformerData.setpFactor(TRANSFORMER_POWER_FACTOR);

        transformerData.setCreateTime(now);
        transformerData.setDeviceTime(deviceTime);

        transformerDataMapper.insert(transformerData);
    }

    /**
     * 计算光伏组串的平均温度
     * 算法：当只有一个有效温度值时，认为无效
     *      当只有两个温度值,检查差值超过阈值泽认为无效，返回NULL，如果不大于阈值，认为有效，取平均值
     *      当多于两个温度值是，依次检查某一值与其他值的平均值的差值，如果大于阈值，认为无效，去除该值；去除后少于两个值的情况返回NULL，多于两个值的时候去平均值
     * @param pvStringTemperatrues
     * @return
     */
    private Float calculatePvStringTemperature(List<TemperatureData> pvStringTemperatrues) {
        if (pvStringTemperatrues.size() < 2) {
            return null;
        } else if (pvStringTemperatrues.size() == 2) {
            if (pvStringTemperatrues.get(0).getTemperature() - pvStringTemperatrues.get(1).getTemperature() > TEMPERATURE_DIFF_THRESHOLD
                || pvStringTemperatrues.get(1).getTemperature() - pvStringTemperatrues.get(0).getTemperature() > TEMPERATURE_DIFF_THRESHOLD) {
                return null;
            } else {
                return (pvStringTemperatrues.get(0).getTemperature() + pvStringTemperatrues.get(1).getTemperature()) / 2;
            }
        } else {
            List<Float> validTemperatures = new ArrayList<>();
            for (int i = 0; i < pvStringTemperatrues.size(); i++) {
                float temperature = pvStringTemperatrues.get(i).getTemperature();
                float temperatureSum = 0;
                for (int j = 0; j < pvStringTemperatrues.size(); j++) {
                    if (j != i) {
                        temperatureSum += pvStringTemperatrues.get(j).getTemperature();
                    }
                }
                float temperatureAvg = temperatureSum / (pvStringTemperatrues.size() - 1);
                if (temperature - temperatureAvg > TEMPERATURE_DIFF_THRESHOLD || temperatureAvg - temperature > TEMPERATURE_DIFF_THRESHOLD) {
                    continue;
                } else {
                    validTemperatures.add(temperature);
                }
            }
            if (validTemperatures.size() < 2 ) {
                return null;
            } else {
                float temperatureSum = 0;
                for (int i = 0; i < validTemperatures.size(); i++) {
                    temperatureSum += validTemperatures.get(i);
                }
                return temperatureSum / validTemperatures.size();
            }
        }
    }
}
