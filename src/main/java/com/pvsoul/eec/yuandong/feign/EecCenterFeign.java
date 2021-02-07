package com.pvsoul.eec.yuandong.feign;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import feign.Headers;
import feign.RequestLine;
public interface EecCenterFeign {
    @RequestLine("POST /test")
    @Headers("Content-Type: application/json")
    public ResultDto test();
}
