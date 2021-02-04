package com.pvsoul.eec.yuandong.dto.devicedata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JinLangDataDto implements Serializable {

    private static final long serialVersionUID = 1891158282147205361L;

    //数据时间
    @JsonProperty("time")
    private Date time;

    //采集器SN
    @JsonProperty("sn")
    private String sn;

    //是否历史。0:实时,1:历史
    @JsonProperty("is_realtime")
    private int isRealtime;

    //采集器信息
    @JsonProperty("collector1")
    private CollectorDataDto collector1;

    //逆变器信息
    @JsonProperty("inverter1")
    private List<InverterDataDto>  inverter1;

}
