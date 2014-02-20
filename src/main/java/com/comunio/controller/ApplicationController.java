package com.comunio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
import com.comunio.service.MatchdayService;
import com.comunio.service.ResultService;
import com.comunio.service.TeamService;

@Controller
public class ApplicationController {
    EntityManager entityManager;
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
    private Comunio comunio;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @RequestMapping("/index")
    public String setupForm(Map<String, Object> map) {
        return "index";
    }

    @RequestMapping("/addComunio")
    public String addComunio() {
        return "addComunio";
    }

    @RequestMapping(value = "/showComunio/{comunioId}/{groupName}")
    public String showComunio(@PathVariable String groupName, @PathVariable String comunioId, Map<String, Object> map) {
        comunio = comunioService.getComunio(Long.parseLong(comunioId));
        Groupe group = groupService.getGroup(Long.parseLong(comunioId), groupName);
        map.put("comunio", comunio);
        map.put("group", group);
        map.put("groupNames", groupService.determineGroupNames(Long.parseLong(comunioId)));
        map.put("matchdays", matchdayService.getSortedMatchdays(group));
        return "overview";
    }

    @RequestMapping(value = "/saveComunio", method = RequestMethod.POST)
    public String addTeams(@RequestParam("teams") String teamsString, @RequestParam("comunioName") String comunioName,
            @RequestParam("password") String password, @RequestParam("numberOfTeams") String numberOfTeams,
            Map<String, Object> map) {

        long comunioId = comunioService.createComunio(comunioName, password);
        groupService.initializeGroups(comunioId, Integer.parseInt(numberOfTeams), teamsString);
        comunio = comunioService.getComunio(comunioId);
        List<Groupe> groups = groupService.findGroupsByComunioId(comunioId);
        map.put("comunio", comunio);
        map.put("groups", groups);
        map.put("group", getGroup(groups, "A"));
        return "overview";
    }

    @RequestMapping("/admin/{comunioId}")
    public String admin(@PathVariable String comunioId, Map<String, Object> map) {
        List<String> teamNames = teamService.findTeamNamesByComunioId(Long.parseLong(comunioId));
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            map.put("teams", objectMapper.writeValueAsString(teamNames));
        } catch (Exception e) {
        }
        map.put("numberOfMatchdays", NUMBER_OF_MATCHDAYS);
        return "admin";
    }

    @RequestMapping("/updateComunio/{comunioId}")
    public void updateComunio(@RequestBody String jsonString, @PathVariable String comunioId) {
        List<?> resultList = parseResultListFromJSON(jsonString);
        if (comunio != null) {
            resultService.updateResult(resultList, comunio.getComunioId());
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
