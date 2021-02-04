package com.pvsoul.eec.yuandong.dto.devicedata;

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
public class UIDataDto implements Serializable {

    private static final long serialVersionUID = -4394464377903668732L;

    //电压,单位 V
    @JsonProperty("u")
    private float u;

    //电流,单位 A
    @JsonProperty("i")
    private float i;

}