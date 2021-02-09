package com.pvsoul.eec.yuandong.dto.web.request;

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
public class GetDevicesStatusInfoRequestDto implements Serializable {

    private static final long serialVersionUID = 3057174735508891995L;

    private int deviceTypeCode;
}
