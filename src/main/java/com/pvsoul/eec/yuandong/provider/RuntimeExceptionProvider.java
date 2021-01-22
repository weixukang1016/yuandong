package com.pvsoul.eec.yuandong.provider;


import com.pvsoul.eec.yuandong.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class RuntimeExceptionProvider implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        log.error("系统错误：", e);
        ResultDto resultDto = new ResultDto();
        resultDto.setCode("500");
        resultDto.setMsg("系统错误");
        resultDto.setSuccess(false);
        return Response.status(Response.Status.OK).entity(resultDto).build();

    }
}
