package com.comunio.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
import com.comunio.service.TeamService;

@Controller
public class ApplicationController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private ComunioService comunioService;
	@Autowired
	private TeamService teamService;

	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map) {
		return "index";
	}

	@RequestMapping(value="/showComunio/{comunioId}/{groupName}")
	public String showComunio(@PathVariable String groupName, @PathVariable String comunioId,
			Map<String, Object> map) {
		Comunio comunio = comunioService.getComunio(Long.parseLong(comunioId));
		List<Groupe> groups = groupService.findGroupsByComunioId(Long.parseLong(comunioId));
		map.put("comunio", comunio);
		map.put("groups", groups);
		map.put("group", getGroup(groups, groupName));
		return "overview";
	}

	private Groupe getGroup(List<Groupe> groups, String groupName) {
		for(Groupe group : groups){
			if(group.getGroupName().equals(groupName)){
				return group;
			}
		}
		return null;
	}

	@RequestMapping("/addComunio")
	public String addComunio() {
		return "addComunio";
	}

	@RequestMapping(value = "/saveComunio", method = RequestMethod.POST)
	public String addTeams(@RequestParam("teams") String teamsString,
			@RequestParam("comunioName") String comunioName,
			@RequestParam("password") String password,
			@RequestParam("numberOfTeams") String numberOfTeams,
			@RequestParam("numberOfGroups") String numberOfGroups,
			Map<String, Object> map) {

		long comunioId = comunioService.createComunio(comunioName, password);
		groupService.initializeGroups(comunioId,
				Integer.parseInt(numberOfTeams),
				Integer.parseInt(numberOfGroups), teamsString);
		Comunio comunio = comunioService.getComunio(comunioId);
		List<Groupe> groups = groupService.findGroupsByComunioId(comunioId);
		map.put("comunio", comunio);
		map.put("groups", groups);
		map.put("group", getGroup(groups, "A"));
		return "overview";
	}
}
