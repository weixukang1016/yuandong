package com.pvsoul.eec.yuandong.dto.web.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.annotation.JacksonFeatures;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InverterInfoDto implements Serializable {

    private static final long serialVersionUID = 7207868137407200313L;

    private String deviceName;

    private String deviceId;

    private int status;

    private Float u;

    private Float i;

    private Float p;

    private int combinerBoxCount;

}
