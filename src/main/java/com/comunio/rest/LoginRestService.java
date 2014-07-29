package com.comunio.rest;

import java.io.IOException;
import java.io.Serializable;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.comunio.service.ComunioService;

@Path("/findByName")
@Controller
public class LoginRestService implements Serializable{

    @Autowired
    ComunioService comunioService;
    ObjectMapper objectMapper = new ObjectMapper();
    
    @POST
    @Path("/{name}")
    public Response findComunioByName(@PathParam("name") String name) throws JsonGenerationException, JsonMappingException, IOException {
	long comunioId = comunioService.findByName(name);
	if (comunioId == 0) {
	    return Response.status(Status.NOT_FOUND).build();
	}
	return Response.status(Status.OK).entity(objectMapper.writeValueAsString(comunioId)).build();
    }
}
