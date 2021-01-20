package com.pvsoul.eec.yuandong.resource;

import com.alibaba.fastjson.JSONObject;
import com.pvsoul.eec.yuandong.dao.*;
import lombok.extern.slf4j.Slf4j;
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

    @POST
    @Path("/test")
    //@ApiOperation("接收iotda的数据")
    public Response pushData(@Context HttpServletRequest request, JSONObject data) {

        log.info(JSONObject.toJSONString(data));
        //log.info(data);
        return Response.status(Response.Status.OK).build();
    }

}
