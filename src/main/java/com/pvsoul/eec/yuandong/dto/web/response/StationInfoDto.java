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
public class StationInfoDto implements Serializable {

    private static final long serialVersionUID = -8743750449794126126L;

    /**
     * 电站名称
     */
    @JsonProperty("stationName")
    private String stationName;

    /**
     * 电站照片
     */
    @JsonProperty("stationPhoto")
    private String stationPhoto;

    /**
     * 装机容量
     */
    @JsonProperty("capacity")
    private String capacity;

    /**
     * 当日发电量
     */
    @JsonProperty("powerToday")
    private String powerToday;

    /**
     * 当月发电量
     */
    @JsonProperty("powerThisMonth")
    private String powerThisMonth;

    /**
     * 当年发电量
     */
    @JsonProperty("powerThisYear")
    private String powerThisYear;

    /**
     * 累计发电量
     */
    @JsonProperty("powerTotal")
    private String powerTotal;

    /**
     * 温度校正PR
     */
    @JsonProperty("temperaturePr")
    private String temperaturePr;

}
