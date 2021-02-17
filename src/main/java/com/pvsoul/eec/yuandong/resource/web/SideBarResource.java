package com.pvsoul.eec.yuandong.resource.web;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.dto.web.request.GetDevicesStatusInfoRequestDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetDeviceInfoResponseDto;
import com.pvsoul.eec.yuandong.dto.web.response.GetDeviceStatusInfoResponseDto;
import com.pvsoul.eec.yuandong.service.DeviceService;
import com.pvsoul.eec.yuandong.service.MeteorologicalService;
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
import java.util.List;

@Path("api/web/sidebar")
@Component
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SideBarResource {

    @Autowired
    private MeteorologicalService meteorologicalService;

    @Autowired
    private DeviceService deviceService;

    @POST
    @Path("/getmeteodata")
    //@ApiOperation("获取当前气象数据")
    public Response getMeteoData(@Context HttpServletRequest request) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = meteorologicalService.GetMeteoData();
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

    @POST
    @Path("/getdevicesinfo")
    //@ApiOperation("获取设备Bar信息")
    public Response getDevicesInfo(@Context HttpServletRequest request) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = deviceService.getDevicesInfo();
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

    @POST
    @Path("/getdevicestatusinfo")
    //@ApiOperation("获取设备状态bar信息")
    public Response getDevicesStatusInfo(@Context HttpServletRequest request, GetDevicesStatusInfoRequestDto data) {

        //log.info(JSONObject.toJSONString(data));
        ResultDto resultDto = deviceService.getDevicesStatusInfo(data.getDeviceTypeCode());
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }
}
