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
public class GetDeviceListRequestDto implements Serializable {

    private static final long serialVersionUID = -8741933785910821335L;

    private Integer deviceStatus;

    private int pageNum;

    private int pageSize;
}
