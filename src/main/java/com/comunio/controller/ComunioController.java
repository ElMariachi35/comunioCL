package com.comunio.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(value = "/comunio.do", method = RequestMethod.POST)
	public String doActions(@ModelAttribute Comunio comunio,
			BindingResult result, @RequestParam String action,
			Map<String, Object> map) {
		Comunio comunioResult = new Comunio();
		switch (action.toLowerCase()) { // only in Java7 you can put String in
										// switch
		case "add":
			comunioService.add(comunio);
			comunioResult = comunio;
			break;
		case "edit":
			comunioService.edit(comunio);
			comunioResult = comunio;
			break;
		case "delete":
			comunioService.delete(comunio.getComId());
			comunioResult = new Comunio();
			break;
		case "search":
			Comunio searchedComunio = comunioService.getComunio(comunio
					.getComId());
			comunioResult = searchedComunio != null ? searchedComunio
					: new Comunio();
			break;
		}
		map.put("comunio", comunioResult);
		map.put("comunioList", comunioService.getAllComunio());
		return "comunio";
	}
}
