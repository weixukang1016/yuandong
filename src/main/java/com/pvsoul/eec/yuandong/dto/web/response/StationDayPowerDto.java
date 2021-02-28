package com.pvsoul.eec.yuandong.dto.web.response;

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
public class StationDayPowerDto implements Serializable {

    private static final long serialVersionUID = 2180968506507541617L;

    private String deviceDate;

    private Float theoreticalPower;

    private Float actualPower;
}
