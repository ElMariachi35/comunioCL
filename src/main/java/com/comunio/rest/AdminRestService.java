package com.comunio.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.comunio.model.JsonResult;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.service.ComunioService;
import com.comunio.service.ResultService;

@Controller
@Path("/admin")
public class AdminRestService {
    @Autowired
    ResultService resultService;
    @Autowired
    SessionData sessionData;
    @Autowired
    ComunioService comunioService;

    ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Path("/findPoints/{comunioId}/{matchdayNumber}")
    public Response findComunioByName(@PathParam("comunioId") long comunioId,
            @PathParam("matchdayNumber") int matchdayNumber) throws JsonGenerationException, JsonMappingException,
            IOException {
        List<Result> results = resultService.findResultsBy(comunioId, matchdayNumber);
        return Response.status(Status.OK).entity(objectMapper.writeValueAsString(results)).build();
    }

    @POST
    @Path("/save/{comunioId}")
    public Response saveMatchday(@PathParam("comunioId") long comunioId, String json) throws JsonParseException,
            JsonMappingException, IOException {
        List<JsonResult> jsonResults = objectMapper.readValue(json, new TypeReference<List<JsonResult>>() {
        });
        resultService.updateResult(jsonResults, comunioId);
        return Response.status(Status.OK).entity(objectMapper.writeValueAsString("Spieltag wurde gespeichert!"))
                .build();
    }

    @POST
    @Path("/password/{comunioId}/{password}")
    public Response findComunioByName(@PathParam("comunioId") long comunioId, @PathParam("password") String password)
            throws JsonGenerationException, JsonMappingException, IOException {
        boolean isPasswordCorrect = comunioService.checkPassword(comunioId, password);
        if (isPasswordCorrect) {
            return Response.status(Status.OK).entity(objectMapper.writeValueAsString("")).build();
        }
        return Response.status(Status.UNAUTHORIZED).entity(objectMapper.writeValueAsString("")).build();
    }
}
