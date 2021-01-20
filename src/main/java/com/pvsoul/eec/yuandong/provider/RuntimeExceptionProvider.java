package com.pvsoul.eec.yuandong.provider;


import com.pvsoul.eec.yuandong.dao.ResultDao;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class RuntimeExceptionProvider implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        log.error("系统错误：", e);
        ResultDao resultDao = new ResultDao();
        resultDao.setCode("500");
        resultDao.setMsg("系统错误");
        resultDao.setSuccess(false);
        return Response.status(Response.Status.OK).entity(resultDao).build();

    }
}
