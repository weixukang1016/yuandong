package com.pvsoul.eec.yuandong.dto.web.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPvStringDetailResponseDto {

    private String deviceName;

    private String deviceId;

    @JsonProperty("isStandard")
    private boolean isStandard;

    private int status;

    private String u;

    private String i;

    private String p;

    //private Float temperature;

    /**
     * 组件衰减率
     */
    private String decayRate;

    /**
     * 灰尘损失率
     */
    private String dustLossRate;

    /**
     *
     */
    private List<DeviceDataDto> deviceDataOfToday;
}
