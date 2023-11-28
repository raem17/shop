package movies.shop.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/")
public class InicioAdminController {
	
	@RequestMapping("inicio")
	public String inicio() {
		return "admin/index";
	}
}
