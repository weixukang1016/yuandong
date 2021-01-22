package com.pvsoul.eec.yuandong.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TemperatureContentDto implements Serializable {
    private static final long serialVersionUID = -7005437124753337103L;

    @JsonProperty("Device_ID")
    private String deviceId;

    @JsonProperty("Temperature_0")
    private Float temperature0;

    @JsonProperty("Temperature_1")
    private Float temperature1;

    @JsonProperty("Temperature_2")
    private Float temperature2;

    @JsonProperty("Temperature_3")
    private Float temperature3;

    @JsonProperty("Temperature_4")
    private Float temperature4;

    @JsonProperty("Temperature_5")
    private Float temperature5;

    @JsonProperty("Temperature_6")
    private Float temperature6;

    @JsonProperty("Temperature_7")
    private Float temperature7;

    @JsonProperty("Temperature_8")
    private Float temperature8;

    @JsonProperty("Temperature_9")
    private Float temperature9;

}
