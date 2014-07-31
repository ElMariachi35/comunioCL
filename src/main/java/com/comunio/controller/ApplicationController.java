package com.comunio.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunio.model.Comunio;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
import com.comunio.service.MatchdayService;
import com.comunio.service.PlayoffService;
import com.comunio.service.ResultService;
import com.comunio.service.TeamService;

@Controller
public class ApplicationController {

    private static final int NUMBER_OF_MATCHDAYS = 17;

    @Autowired
    private GroupService groupService;
    @Autowired
    private ComunioService comunioService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private MatchdayService matchdayService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private PlayoffService playoffService;
    @Autowired
    private SessionData sessionData;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = { "/index", "", "/" })
    public String setupForm(Map<String, Object> map) {
	return "index";
    }

    @RequestMapping("/register")
    public String addComunio() {
	return "register";
    }

    @RequestMapping(value = "/show/{comunioId}")
    public String showComunio(@PathVariable Long comunioId,
	    Map<String, Object> map) throws JsonGenerationException,
	    JsonMappingException, IOException {
	sessionData.setComunio(comunioService.retrieveComunio(comunioId));
	map.put("comunioJSON",
		objectMapper.writeValueAsString(sessionData.getComunio()));
	return "overview";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addTeams(@RequestParam("teams") String teamsString,
	    @RequestParam("comName") String comunioName,
	    @RequestParam("password") String password,
	    @RequestParam("numberOfTeams") String numberOfTeams,
	    Map<String, Object> map) throws JsonGenerationException,
	    JsonMappingException, IOException {

	Comunio comunio = comunioService.createComunio(comunioName, password);
	sessionData.setComunio(comunio);
	groupService.initializeGroups(Integer.parseInt(numberOfTeams),
		teamsString);
	sessionData.setComunio(comunioService.retrieveComunio(comunio
		.getComunioId()));
	map.put("comunioJSON",
		objectMapper.writeValueAsString(sessionData.getComunio()));
	return "overview";
    }

    @RequestMapping("/admin")
    public String admin(Map<String, Object> map) throws JsonGenerationException, JsonMappingException, IOException {
	Comunio comunio = sessionData.getComunio();
	if(comunio==null) {
	    return "error";
	}
	map.put("comunioJSON",
		objectMapper.writeValueAsString(comunio));
	List<Team> teams = comunioService.getAllTeams();
	map.put("teams", objectMapper.writeValueAsString(teams));
	map.put("numberOfMatchdays", NUMBER_OF_MATCHDAYS);
	return "admin";
    }

    @RequestMapping("/showPlayoff")
    public String showPlayoff(Map<String, Object> map) {
	map.put("comunio", sessionData.getComunio());
	return "playoff";

    }
}
