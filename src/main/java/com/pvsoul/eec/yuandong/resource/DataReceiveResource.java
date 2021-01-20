package com.pvsoul.eec.yuandong.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pvsoul.eec.yuandong.dao.*;
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

//@Api(value = "DataReceiveResource")
@Path("api/datareceive")
@Component
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DataReceiveResource {

    private final static String TOPIC_PREFIX_METEOROLOGICAL = "$oc/devices/";

    private final static String TOPIC_SUFFIX_METEOROLOGICAL = "/user/push/meteorological";

    private final static String TOPIC_SUFFIX_TEMPERATUE = "/user/push/temperature";

    @Autowired
    private MeteorologicalService meteorologicalService;

    @Autowired
    private TemperatureService temperatureService;

    @POST
    @Path("/devicemessagereport")
    //@ApiOperation("接收iotda的数据")
    public Response pushData(@Context HttpServletRequest request, IotdaDataDao data) {

        log.info(JSONObject.toJSONString(data));
        IotdaHeaderDao iotdaHeaderDao = data.getNotifyData().getHeader();
        IotdaBodyDao iotdaBodyDao = data.getNotifyData().getBody();
        String topic = iotdaBodyDao.getTopic();
        ResultDao resultDao = new ResultDao();
        if (topic.equals(TOPIC_PREFIX_METEOROLOGICAL + iotdaHeaderDao.getDeviceId() + TOPIC_SUFFIX_METEOROLOGICAL)) {
            MeteorologicalContentDao content = JSON.toJavaObject(data.getNotifyData().getBody().getContent(), MeteorologicalContentDao.class);
            resultDao = meteorologicalService.SaveData(content);
        } else if (topic.equals(TOPIC_PREFIX_METEOROLOGICAL + iotdaHeaderDao.getDeviceId() + TOPIC_SUFFIX_TEMPERATUE)) {
            TemperatureContentDao content = JSON.toJavaObject(data.getNotifyData().getBody().getContent(), TemperatureContentDao.class);
            resultDao = temperatureService.SaveData(content);
        }
        return Response.status(Response.Status.OK).entity(resultDao).build();
    }

}
