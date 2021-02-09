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
public class GetDeviceInfoResponseDto implements Serializable {

    private static final long serialVersionUID = -5504789850484054670L;

    private String deviceType;

    private int deviceTypeCode;

    private int deviceCount;
}
