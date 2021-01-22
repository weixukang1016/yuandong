package com.pvsoul.eec.yuandong.resource;

//import com.alibaba.fastjson.JSONObject;
import com.pvsoul.eec.yuandong.dto.*;
import com.pvsoul.eec.yuandong.service.MeteorologicalService;
import com.pvsoul.eec.yuandong.service.TemperatureService;
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

@Path("api/tastekletdtu")
@Component
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TastekLetDtuResouce {

    @Autowired
    private MeteorologicalService meteorologicalService;

    @Autowired
    private TemperatureService temperatureService;
/*
    @POST
    @Path("/test")
    //@ApiOperation("接收测试数据")
    public Response receiveTestData(@Context HttpServletRequest request, JSONObject data) {

        //log.info(JSONObject.toJSONString(data));
        //log.info(data);
        return Response.status(Response.Status.OK).build();
    }
*/
    @POST
    @Path("/meteorological")
    //@ApiOperation("接收气象的数据")
    public Response receiveMeteorologicalData(@Context HttpServletRequest request, MeteorologicalContentDto data) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = meteorologicalService.SaveData(data);
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

    @POST
    @Path("/temperature")
    //@ApiOperation("接收背板温度的数据")
    public Response receiveTemperatureData(@Context HttpServletRequest request, TemperatureContentDto data) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = temperatureService.SaveData(data);
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

}
