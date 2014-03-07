package com.comunio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
import com.comunio.service.MatchdayService;
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

    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @RequestMapping(value = { "/index", "", "/" })
    public String setupForm(Map<String, Object> map) {
        return "index";
    }

    @RequestMapping("/add")
    public String addComunio() {
        return "addComunio";
    }

    @RequestMapping(value = "/show/{comunioId}/{groupName}")
    public String showComunio(@PathVariable String groupName, @PathVariable String comunioId, Map<String, Object> map) {
        comunioService.loadComunio(Long.parseLong(comunioId));
        Groupe group = groupService.getGroup(Long.parseLong(comunioId), groupName);
        map.put("comunio", comunioService.getComunio());
        map.put("group", group);
        map.put("groupNames", groupService.determineGroupNames(Long.parseLong(comunioId)));
        map.put("matchdays", matchdayService.getSortedMatchdays(group));
        return "overview";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addTeams(@RequestParam("teams") String teamsString, @RequestParam("comunioName") String comunioName,
            @RequestParam("password") String password, @RequestParam("numberOfTeams") String numberOfTeams,
            Map<String, Object> map) {

        long comunioId = comunioService.createComunio(comunioName, password);
        groupService.initializeGroups(comunioId, Integer.parseInt(numberOfTeams), teamsString);
        comunioService.refreshComunio();
        List<Groupe> groups = groupService.findGroupsByComunioId(comunioId);
        map.put("comunio", comunioService.getComunio());
        map.put("groups", groups);
        map.put("group", getGroup(groups, "A"));
        return "overview";
    }

    @RequestMapping("/admin/{comunioId}")
    public String admin(@PathVariable String comunioId, Map<String, Object> map) {
        List<Team> teams = comunioService.getAllTeams(Long.parseLong(comunioId));
        try {
            map.put("teams", objectMapper.writeValueAsString(teams));
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("numberOfMatchdays", NUMBER_OF_MATCHDAYS);
        map.put("comunio", comunioService.getComunio());
        return "admin";
    }

    @RequestMapping("/updateComunio/{comunioId}")
    public void updateComunio(@RequestBody String jsonString, @PathVariable String comunioId) {
        List<?> resultList = parseResultListFromJSON(jsonString);
        if (comunioService.getComunio() != null) {
            resultService.updateResult(resultList, comunioService.getComunio().getComunioId());
            comunioService.refreshComunio();
        }
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

    private Groupe getGroup(List<Groupe> groups, String groupName) {
        for (Groupe group : groups) {
            if (group.getGroupName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }
}
