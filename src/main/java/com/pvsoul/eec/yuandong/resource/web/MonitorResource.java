package com.pvsoul.eec.yuandong.resource.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetDeviceListRequestDto;
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
    public Response getInverterList(@Context HttpServletRequest request, GetDeviceListRequestDto getDeviceListRequestDto) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = deviceService.getInverterInfoList(getDeviceListRequestDto);
        String result = JSONObject.toJSONString(resultDto, SerializerFeature.WriteMapNullValue);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Path("/getcombinerboxlist")
    //@ApiOperation("获取汇流箱列表")
    public Response getCombinerBoxList(@Context HttpServletRequest request, GetDeviceListRequestDto getDeviceListRequestDto) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = deviceService.getCombinerBoxInfoList(getDeviceListRequestDto);
        String result = JSONObject.toJSONString(resultDto, SerializerFeature.WriteMapNullValue);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Path("/getpvstringlist")
    //@ApiOperation("获取光伏组串列表")
    public Response getPvstringList(@Context HttpServletRequest request, GetDeviceListRequestDto getDeviceListRequestDto) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = deviceService.getPvStringInfoList(getDeviceListRequestDto);
        String result = JSONObject.toJSONString(resultDto, SerializerFeature.WriteMapNullValue);
        return Response.status(Response.Status.OK).entity(result).build();
    }

}
