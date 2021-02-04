package com.pvsoul.eec.yuandong.dto.devicedata;

import com.alibaba.fastjson.JSONObject;
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
public class IotdaBodyDto implements Serializable {
    private static final long serialVersionUID = 1389033345147498788L;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("content")
    private JSONObject content;

}
