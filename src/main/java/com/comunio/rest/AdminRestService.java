package com.comunio.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.comunio.exception.PlayoffCannotBeInitializedException;
import com.comunio.model.JsonResult;
import com.comunio.model.Result;
import com.comunio.model.SessionData;
import com.comunio.service.ComunioService;
import com.comunio.service.ResultService;

@Controller
@Path("/admin")
public class AdminRestService {
	private static final String SAVE_FAILED_MESSAGE = "KO-Runde konnte nicht erstellt werden, "
			+ "möglicherweise sind nicht alle vorherigen Runden korrekt eingegeben worden";
	private static final String SAVE_OK_MESSAGE = "Spieltage wurde gespeichert!";
	@Autowired
	ResultService resultService;
	@Autowired
	SessionData sessionData;
	@Autowired
	ComunioService comunioService;

	ObjectMapper objectMapper = new ObjectMapper();

	@POST
	@Path("/findPoints/{comunioId}/{matchdayNumber}")
	public Response findComunioByName(@PathParam("comunioId") long comunioId, @PathParam("matchdayNumber") int matchdayNumber)
			throws JsonGenerationException, JsonMappingException, IOException {
		List<Result> results = resultService.findResultsBy(comunioId, matchdayNumber);
		return Response.status(Status.OK).entity(objectMapper.writeValueAsString(results)).build();
	}

	@POST
	@Path("/findNextMatchday/{comunioId}")
	public Response findNextMatchday(@PathParam("comunioId") long comunioId) throws JsonGenerationException, JsonMappingException,
			IOException {
		int nextMatchday = resultService.findNextMatchday(comunioId);
		return Response.status(Status.OK).entity(objectMapper.writeValueAsString(nextMatchday)).build();
	}

	@POST
	@Path("/save/{comunioId}")
	public Response saveMatchday(@PathParam("comunioId") long comunioId, String json) throws JsonGenerationException, JsonMappingException,
			IOException {
		List<JsonResult> jsonResults = objectMapper.readValue(json, new TypeReference<List<JsonResult>>() {
		});
		try {
			resultService.updateResult(jsonResults, comunioId);
		} catch (PlayoffCannotBeInitializedException e) {
			return createResponse(Status.PRECONDITION_FAILED, SAVE_FAILED_MESSAGE);
		}
		return createResponse(Status.OK, SAVE_OK_MESSAGE);
	}

	private Response createResponse(Status status, String message) throws JsonGenerationException, JsonMappingException, IOException {
		return Response.status(status).entity(objectMapper.writeValueAsString(message)).build();
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
