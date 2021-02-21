package com.pvsoul.eec.yuandong.dto.web.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPvStringDetailDto {

    private String deviceName;

    private String deviceId;

    private boolean isStandard;

    private int status;

    private String u;

    private String i;

    private String p;

    //private Float temperature;

    private String decayRate;
}
