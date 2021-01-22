package com.pvsoul.eec.yuandong.resource;

//import com.alibaba.fastjson.JSONObject;
import com.pvsoul.eec.yuandong.dto.JinLangDataDto;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.service.JinlangService;
import com.pvsoul.eec.yuandong.util.JinlangAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("api/datareceive")
@Component
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JinlangResource {

    @Autowired
    private JinlangService jinlangService;

    @POST
    @Path("/pushdata")
    //@ApiOperation("接收锦浪云平台推送数据")
    public Response receiveJinlangData(@Context HttpServletRequest request, JinLangDataDto data) throws ParseException {
        String verb = request.getMethod();
        String contentType = request.getHeader("content-type");
        String date = request.getHeader("date");
        String canonicalizedResource = request.getRequestURI();
        String authorization = request.getHeader("authorization");
        boolean isAuth = JinlangAuth.checkAuth(verb, contentType, date, canonicalizedResource, authorization);
        ResultDto resultDto;
        isAuth = true;
        if (isAuth) {
            //log.info(JSONObject.toJSONString(data));
            resultDto = jinlangService.SaveData(data);
        } else {
            log.info("Jinlang Authorization is wrong");
            log.info("verb:" + verb);
            log.info("contentType:" + contentType);
            log.info("date:" + date);
            log.info("canonicalizedResource:" + canonicalizedResource);
            log.info("authorization:" + authorization);
            log.info("RemoteHost:" + request.getRemoteHost());
            resultDto = new ResultDto();
            resultDto.setSuccess(false);
            resultDto.setCode(String.valueOf(Response.Status.UNAUTHORIZED.getStatusCode()));
            resultDto.setMsg("Jinlang Authorization is wrong");
        }
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

}
