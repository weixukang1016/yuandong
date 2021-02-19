package com.pvsoul.eec.yuandong.resource.web;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetPvstringListRequestDto;
import com.pvsoul.eec.yuandong.service.DeviceService;
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

@Path("api/web/monitor")
@Component
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MonitorResource {

    @Autowired
    private DeviceService deviceService;

    @POST
    @Path("/getinverterlist")
    //@ApiOperation("获取逆变器列表")
    public Response getInverterList(@Context HttpServletRequest request) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = new ResultDto();
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

    @POST
    @Path("/getpvstringlist")
    //@ApiOperation("获取光伏组串列表")
    public Response getPvstringList(@Context HttpServletRequest request, GetPvstringListRequestDto getPvstringListRequestDto) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = deviceService.getPvStringInfoList(getPvstringListRequestDto);
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

}
