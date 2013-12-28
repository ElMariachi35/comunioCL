package com.comunio.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comunio.model.Comunio;
import com.comunio.service.ComunioService;

@Controller
public class ComunioController {
	@Autowired
	private ComunioService comunioService;

	@RequestMapping("/index")
	public String setupForm(Map<String, Object> map) {
		Comunio comunio = new Comunio();
		map.put("comunio", comunio);
		map.put("comunioList", comunioService.getAllComunio());
		return "comunio";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String addComunio(@RequestParam("name") String name, @RequestParam("numberOfGroups") String numberOfGroups, Map<String, Object> map){
		comunioService.createComunio(name, Integer.parseInt(numberOfGroups));
		map.put("comunioList", comunioService.getAllComunio());
		return "comunio";
	}
}
