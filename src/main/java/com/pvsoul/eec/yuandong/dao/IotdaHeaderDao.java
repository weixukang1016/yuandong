package com.pvsoul.eec.yuandong.dao;

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
public class IotdaHeaderDao implements Serializable {
    private static final long serialVersionUID = 8956364323471511064L;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("node_id")
    private String nodeId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("gateway_id")
    private String gatewayId;

}
