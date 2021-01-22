package com.pvsoul.eec.yuandong.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InverterDataDto implements Serializable {

    private static final long serialVersionUID = 7921611259645202229L;

    //逆变器SN
    @JsonProperty("inverter_sn")
    private String inverterSn;

    //逆变器型号
    @JsonProperty("inverter_model")
    private String inverterModel;

    //逆变器容量,单位kWp
    @JsonProperty("rated_power")
    private float ratedPower;

    //逆变器版本
    @JsonProperty("inverter_ver")
    private String inverterVer;

    //当日电量,单位kWh
    @JsonProperty("e_today")
    private float eToday;

    //当月电量,单位kWh
    @JsonProperty("e_month")
    private float eMonth;

    //当年电量,单位kWh
    @JsonProperty("e_year")
    private float eYear;

    //累计电量,单位kWh
    @JsonProperty("e_total")
    private float eTotal;

    //直流
    @JsonProperty("pv")
    private List<UIDataDto> pv;

    //逆变器温度,单位 ℃
    @JsonProperty("inverter_temp")
    private float inverterTemp;

    //逆变器状态
    @JsonProperty("state")
    private String state;

    //报警中文
    @JsonProperty("alarm_cn")
    private String alarmCn;

    //报警英文
    @JsonProperty("alarm_en")
    private String alarmEn;

    //交流
    @JsonProperty("ac")
    private List<UIDataDto> ac;

    //交流频率，单位 Hz
    @JsonProperty("fac")
    private float fac;

    //逆变器功率，单位 W
    @JsonProperty("pac")
    private float pac;

    //国家标准代码
    @JsonProperty("national_code")
    private int nationalCode;

    //国家标准
    @JsonProperty("national")
    private String national;

}