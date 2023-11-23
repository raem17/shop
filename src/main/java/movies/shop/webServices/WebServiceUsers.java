package movies.shop.webServices;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import movies.shop.model.User;
import movies.shop.services.UsersService;

@Controller
@RestController
@RequestMapping("webServiceUsers/")
public class WebServiceUsers {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("userRegister")
	public String userRegister (@RequestParam Map<String, Object> formData, MultipartHttpServletRequest request) {
		
		Gson gson = new Gson();
		JsonElement json = gson.toJsonTree(formData);
		User u = gson.fromJson(json, User.class);
		
		u.setAvatar(request.getFile("avatar"));
		try {
			u.setImageInBytes(request.getFile("avatar").getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		usersService.createUser(u);
		
		return "ok";
	}
	
	@RequestMapping("userLogin")
	public String userLogin (String email, String pass, HttpServletRequest request) {
		User u = usersService.getUserByEmailAndPass(email, pass);
		String res = "";
		
		if (u != null) {
			request.getSession().setAttribute("usuario_identificado", u);
			res = "ok, " + u.getFirst_name();

		} else {
			res = "Email o pass incorrectos.";
		}
		
		return res;
	}
	
	@RequestMapping("logout")
	public String logout (HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "ok";
	}
	
	@RequestMapping("userExistsInSession")
	public String userExistsInSession (HttpServletRequest request) {
		boolean exists = false;
		
		if (request.getSession().getAttribute("usuario_identificado") != null) {
			exists = true;
		}
		
		String res = String.valueOf(exists);
		
		return res;
	}
}

