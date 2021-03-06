package com.pvsoul.eec.yuandong.dto.web.response;

import com.alibaba.fastjson.annotation.JSONField;
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
public class PvStringInfoDto implements Serializable {

    private static final long serialVersionUID = 5099757433940155886L;

    private String deviceName;

    private String deviceId;

    @JsonProperty("isStandard")
    private boolean isStandard;

    private int status;

    private String u;

    private String i;

    private String p;

    private String temperature;
}
