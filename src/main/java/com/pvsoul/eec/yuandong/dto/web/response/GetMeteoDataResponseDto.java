package com.pvsoul.eec.yuandong.dto.web.response;

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
public class GetMeteoDataResponseDto implements Serializable {

    private static final long serialVersionUID = -3350796454227917987L;

    /**
     * 气温（单位：℃）
     */
    @JsonProperty("temperature")
    private Float temperature;

    /**
     * 相对湿度
     */
    @JsonProperty("humidity")
    private Float humidity;

    /**
     * 降雨(0：未降雨，1：降雨）
     */
    @JsonProperty("rainFall")
    private String rainFall;

    /**
     * 大气压（单位：帕）
     */
    @JsonProperty("pressure")
    private Float pressure;

    /**
     * 风向（0~15的值，分别代表22.5度，0：北风，8：南风，4：东风，12：西风
     */
    @JsonProperty("windDirection")
    private String windDirection;

    /**
     * 风速
     */
    @JsonProperty("windSpeed")
    private Float windSpeed;

    /**
     * 太阳总辐射（单位：W/㎡）
     */
    @JsonProperty("poa")
    private Float poa;
}
