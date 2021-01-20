package com.pvsoul.eec.yuandong.provider;


import com.pvsoul.eec.yuandong.dao.ResultDao;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ValidationExceptionProvider implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException e) {
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> cv : ((ConstraintViolationException) e).getConstraintViolations()) {
            strBuilder.append(cv.getMessage());
            strBuilder.append(";");
        }

        ResultDao resultDao = new ResultDao();
        resultDao.setCode("500");
        resultDao.setMsg(strBuilder.toString());
        resultDao.setSuccess(false);
        return Response.status(Response.Status.OK).entity(resultDao).build();
    }
}
