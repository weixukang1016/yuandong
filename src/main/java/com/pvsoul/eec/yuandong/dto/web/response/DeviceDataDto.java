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
public class DeviceDataDto implements Serializable {

    private static final long serialVersionUID = 3885021070282460512L;

    private String time;

    private Float u;

    private Float i;

    private Float p;

    private Float temperature;
}
