package com.pvsoul.eec.yuandong.service.impl;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.feign.EecCenterFeign;
import com.pvsoul.eec.yuandong.service.EecCenterService;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EecCenterServiceImpl implements EecCenterService {

    @Value("${feign.eeccenter-baseurl}")
    private String baseUrl;

    public ResultDto test() {

        EecCenterFeign eecCenterFeign = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                .target(EecCenterFeign.class, baseUrl);
        ResultDto resultDto = eecCenterFeign.test();
        return resultDto;
    }
}
