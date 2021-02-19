package com.pvsoul.eec.yuandong.dto.web.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class PvStringInfoDto implements Serializable {

    private static final long serialVersionUID = 5099757433940155886L;

    private String deviceName;

    private String deviceId;

    private boolean isStandard;

    private int status;

    private float u;

    private float i;

    private float p;

    private float temperature;
}
