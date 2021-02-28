package com.pvsoul.eec.yuandong.resource.web;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.service.StationService;
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

@Path("api/web/home")
@Component
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {

    @Autowired
    private StationService stationService;

    @POST
    @Path("/getpowerstationinfo")
    //@ApiOperation("电站信息")
    public Response getPowerStationInfo(@Context HttpServletRequest request) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = stationService.getPowerStationInfo();
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

    @POST
    @Path("/getStationDayPower")
    //@ApiOperation("获取电站每日发电量（30天以内）")
    public Response getStationDayPower(@Context HttpServletRequest request) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = stationService.getDayPower();
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

}
