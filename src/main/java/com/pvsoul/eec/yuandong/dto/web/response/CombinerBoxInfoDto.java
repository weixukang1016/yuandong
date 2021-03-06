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
public class CombinerBoxInfoDto implements Serializable {

    private static final long serialVersionUID = -3631272243833728501L;

    private String deviceName;

    private String deviceId;

    private int status;

    private String u;

    private String i;

    private String p;

    private String temperature;

    private int pvStringCount;
}
