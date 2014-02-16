package com.comunio.controller;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
import com.comunio.service.MatchdayService;
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
        Comunio comunio = comunioService.getComunio(Long.parseLong(comunioId));
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
        Comunio comunio = comunioService.getComunio(comunioId);
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
    public @ResponseBody
    String updateComunio(@RequestBody String jsonString, @PathVariable String comunioId) {
        System.out.println(jsonString);
        return "Comunio updated";
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
