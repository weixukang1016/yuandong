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
public class GetDeviceStatusInfoResponseDto implements Serializable {

    private static final long serialVersionUID = -3435566425893955754L;

    private String deviceStauts;

    private Integer deviceStautsCode;

    private int deviceCount;
}
