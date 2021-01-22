package com.pvsoul.eec.yuandong.dto;

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
public class IotdaDataDto implements Serializable {
    private static final long serialVersionUID = 9058328430384287451L;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("event")
    private String event;

    @JsonProperty("event_time")
    private String eventTime;

    @JsonProperty("notify_data")
    private IotdaNotifyDataDto notifyData;

}
