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
public class TransformerInfoDto implements Serializable {

    private static final long serialVersionUID = -3289683934143392257L;

    private String deviceName;

    private String deviceId;

    private int status;

    private Float lU;

    private Float hU;

    private Float pac;

    private Float fac;

    private Float pFactor;

    private int inverterCount;
}
