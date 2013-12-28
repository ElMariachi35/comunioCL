package com.comunio.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunio.model.Comunio;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;

@Controller
public class ComunioController {
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
			@RequestParam("password") String password, Map<String, Object> map) {
		long comunioId = comunioService.createComunio(name, password);
		map.put("comunioList", comunioService.getAllComunio());
		map.put("comunio", comunioService.getComunio(comunioId));
		return "add";
	}

	@RequestMapping(value = "inputComunioSize", method = RequestMethod.POST)
	public String addComunioSize(
			@RequestParam("numberOfTeams") String numberOfTeams,
			@RequestParam("numberOfGroups") String numberOfGroups,
			@RequestParam("comunioId") String comunioId,
			Map<String, Object> map) {
		
			groupService.initializeGroups(Long.parseLong(comunioId), Integer.parseInt(numberOfTeams), Integer.parseInt(numberOfGroups));
			Comunio comunio = comunioService.getComunio(Long.parseLong(comunioId));
			map.put("groups", comunio.getGroups());
		return "add";
	}
}
