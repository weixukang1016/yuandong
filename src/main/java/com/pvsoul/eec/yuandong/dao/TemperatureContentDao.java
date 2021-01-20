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
public class TemperatureContentDao implements Serializable {
    private static final long serialVersionUID = -7005437124753337103L;

    @JSONField(name="Device_ID")
    private String deviceId;

    @JSONField(name="Temperature_0")
    private Float temperature0;

    @JSONField(name="Temperature_1")
    private Float temperature1;

    @JSONField(name="Temperature_2")
    private Float temperature2;

    @JSONField(name="Temperature_3")
    private Float temperature3;

    @JSONField(name="Temperature_4")
    private Float temperature4;

    @JSONField(name="Temperature_5")
    private Float temperature5;

    @JSONField(name="Temperature_6")
    private Float temperature6;

    @JSONField(name="Temperature_7")
    private Float temperature7;

    @JSONField(name="Temperature_8")
    private Float temperature8;

    @JSONField(name="Temperature_9")
    private Float temperature9;

}
