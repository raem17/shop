package movies.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import movies.shop.utilitiesSetup.SetupService;

@Controller
public class InicioController {
	
	@Autowired
	private SetupService setupService;
	
	@RequestMapping("")
	public String inicio() {
		setupService.prepareSetup();
		
		return "inicio";
	}
}
