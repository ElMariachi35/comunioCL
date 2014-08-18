package com.comunio.rest;

import java.io.Serializable;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.comunio.service.ComunioService;

@Controller
@Path("/register")
public class RegisterRestService implements Serializable {

    @Autowired
    ComunioService comunioService;

    @POST
    @Path("/unique/{comName}")
    public Response isComNameUnique(@PathParam("comName") String comName) {
        if (comunioService.isComNameUnique(comName)) {
            return Response.status(Status.OK).entity("true").build();
        }
        return Response.status(Status.CONFLICT).build();
    }
}
