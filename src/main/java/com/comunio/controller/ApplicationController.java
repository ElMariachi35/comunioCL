package com.comunio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
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

    @RequestMapping("/add")
    public String addComunio() {
        return "addComunio";
    }

    @RequestMapping(value = "/show/{comunioId}/{groupName}")
    public String showComunio(@PathVariable String groupName, @PathVariable String comunioId, Map<String, Object> map)
            throws JsonGenerationException, JsonMappingException, IOException {
        sessionData.setComunio(comunioService.retrieveComunio(Long.parseLong(comunioId)));
        map.put("comunio", sessionData.getComunio());
        Groupe group = groupService.getGroup(groupName);
        map.put("group", group);
        map.put("teams", group.getSortedTeams());
        map.put("groupNames", groupService.determineGroupNames(groupService.getGroups().size()));
        map.put("matchdays", matchdayService.getSortedMatchdays(group.getFixture()));
        return "overview";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addTeams(@RequestParam("teams") String teamsString, @RequestParam("comunioName") String comunioName,
            @RequestParam("password") String password, @RequestParam("numberOfTeams") String numberOfTeams,
            Map<String, Object> map) {

        Comunio comunio = comunioService.createComunio(comunioName, password);
        sessionData.setComunio(comunio);
        groupService.initializeGroups(Integer.parseInt(numberOfTeams), teamsString);
        sessionData.setComunio(comunioService.retrieveComunio(comunio.getComunioId()));
        List<Groupe> groups = new ArrayList<Groupe>(groupService.getGroups());
        map.put("comunio", sessionData.getComunio());
        Groupe group = groupService.getGroup("A");
        map.put("group", group);
        map.put("teams", group.getSortedTeams());
        map.put("groupNames", groupService.determineGroupNames(groups.size()));

        map.put("matchdays", matchdayService.getSortedMatchdays(group.getFixture()));
        return "overview";
    }

    @RequestMapping("/admin")
    public String admin(Map<String, Object> map) {
        List<Team> teams = comunioService.getAllTeams();
        try {
            map.put("teams", objectMapper.writeValueAsString(teams));
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("numberOfMatchdays", NUMBER_OF_MATCHDAYS);
        map.put("comunio", sessionData.getComunio());
        return "admin";
    }

    @RequestMapping("/updateComunio/{comunioId}")
    public void updateComunio(@RequestBody String jsonString, @PathVariable String comunioId) {
        List<?> resultList = parseResultListFromJSON(jsonString);
        resultService.updateResult(resultList, Long.parseLong(comunioId));
    }

    @RequestMapping("/showPlayoff")
    public String showPlayoff(Map<String, Object> map) {
        map.put("comunio", sessionData.getComunio());
        return "playoff";

    }

    private List<?> parseResultListFromJSON(String jsonString) {
        List<?> jsonObject = null;
        try {
            jsonObject = objectMapper.readValue(jsonString, ArrayList.class);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
