package com.pvsoul.eec.yuandong.dto.web.response;

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
public class GetPowerStationInfoDto implements Serializable {

    private static final long serialVersionUID = 5656879715019881453L;

    /**
     * 电站信息
     */
    @JsonProperty("stationInfo")
    private StationInfoDto stationInfoDto;

    /**
     * 设备概况
     */
    @JsonProperty("deviceOverview")
    private List deviceOverview;


}
