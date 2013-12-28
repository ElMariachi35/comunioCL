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
import com.comunio.model.Groupe;
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
			Groupe group = new Groupe();
			group.setGroupId(0);
			group.setComunio(comunio);
			group.setGroupName("A");
			groupService.add(group);
			comunioResult = comunio;
			break;
		case "edit":
			comunioService.edit(comunio);
			comunioResult = comunio;
			break;
		case "delete":
			comunioService.delete(comunio.getComunioId());
			comunioResult = new Comunio();
			break;
		case "search":
			Comunio searchedComunio = comunioService.getComunio(comunio
					.getComunioId());
			if (searchedComunio != null) {
				comunioResult = searchedComunio;
				System.out.println(comunioResult.getGroups().size());
			} else {
				comunioResult = new Comunio();
			}
			break;
		}
		map.put("comunio", comunioResult);
		map.put("comunioList", comunioService.getAllComunio());
		return "comunio";
	}
}
