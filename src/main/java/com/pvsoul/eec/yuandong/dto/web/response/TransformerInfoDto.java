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

    private String lU;

    private String hU;

    private String pac;

    private String fac;

    private String pFactor;

    private int inverterCount;
}
