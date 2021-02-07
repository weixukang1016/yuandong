package com.pvsoul.eec.yuandong.resource;

import com.pvsoul.eec.yuandong.dto.ResultDto;
import com.pvsoul.eec.yuandong.service.EecCenterService;
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

@Path("api/eeccenter")
@Component
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EecCenterResource {

    @Autowired
    private EecCenterService eecCenterService;

    @POST
    @Path("/test")
    //@ApiOperation("test")
    public Response test(@Context HttpServletRequest request) {

        log.info("matlab test");
        ResultDto resultDto = eecCenterService.test();
        return Response.status(Response.Status.OK).entity(resultDto).build();
    }

}
