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
public class CollectorDataDto implements Serializable {

    private static final long serialVersionUID = 1362577570741825220L;

    //采集器版本
    @JsonProperty("collector_ver")
    private String collectorVer;

    //采集器类型。0：gprs,1:wifi
    @JsonProperty("type")
    private int type;

    //采集器信号强度,1-5 级
    @JsonProperty("rssi_level")
    private int rssiLevel;

    //采集器信号值。Gprs 为1 到31,Wifi 为-40 到-100
    @JsonProperty("rssi")
    private int rssi;

}
