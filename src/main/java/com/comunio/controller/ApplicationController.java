package com.comunio.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunio.model.Comunio;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;

@Controller
public class ApplicationController {
	@Autowired
	private ComunioService comunioService;
	@Autowired
	private GroupService groupService;

	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map) {
		// Comunio comunio = new Comunio();
		// map.put("comunio", comunio);
		// map.put("comunioList", comunioService.getAllComunio());
		return "index";
	}

	@RequestMapping("/addComunio")
	public String addComunio() {
		return "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addComunio(@RequestParam("name") String name,
			@RequestParam("numberOfTeams") String numberOfTeamsStr,
			@RequestParam("numberOfGroups") String numberOfGroupsStr,
			@RequestParam("password") String password, Map<String, Object> map) {

		long comunioId = comunioService.createComunio(name, password);

		int numberOfTeams = Integer.parseInt(numberOfTeamsStr);
		int numberOfGroups = Integer.parseInt(numberOfGroupsStr);

		groupService.initializeGroups(comunioId, numberOfTeams, numberOfGroups);

		Comunio comunio = comunioService.getComunio(comunioId);
		map.put("comunio", comunio);
		map.put("groups", comunio.getGroups());
		map.put("numberOfTeams", numberOfTeams);

		return "addTeams";
	}
	
	@RequestMapping(value="/addTeams", method=RequestMethod.POST)
	public String addTeams(@RequestParam("teams") String teamsString){
		System.out.println(teamsString);
		return "index";
	}
}
