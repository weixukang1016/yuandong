package com.pvsoul.eec.yuandong.dao;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteorologicalContentDao implements Serializable {
    private static final long serialVersionUID = -5683478790869883064L;

    @JSONField(name="Device_ID")
    private String deviceId;

    /**
     * 气温（单位：℃）
     */
    @JSONField(name="Temperature")
    private Float temperature;

    /**
     * 相对湿度
     */
    @JSONField(name="Humidity")
    private Float humidity;

    /**
     * 降雨(0：未降雨，1：降雨）
     */
    @JSONField(name="RainFall")
    private Integer rainFall;

    /**
     * 大气压（单位：帕）
     */
    @JSONField(name="Pressure")
    private Float pressure;

    /**
     * 风向（0~15的值，分别代表22.5度，0：北风，8：南风，4：东风，12：西风
     */
    @JSONField(name="WindDirection")
    private Integer windDirection;

    /**
     * 风速
     */
    @JSONField(name="WindSpeed")
    private Float windSpeed;

    /**
     * 太阳总辐射（单位：W/㎡）
     */
    @JSONField(name="Irradiance")
    private Float irradiance;
}
