package movies.shop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import movies.shop.model.User;
import movies.shop.services.UsersService;


@Controller
@RequestMapping("admin/")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("getUsers")
	public String getUsers(Model model) {
		List<User> users = usersService.getUsers();
		model.addAttribute("users", users);
		
		return "admin/users";
	}
	
	@RequestMapping("deleteUser")
	public String deleteUser(String id, Model model) {
		usersService.deleteUserByID(Integer.parseInt(id));
		
		return getUsers(model);
	}
	
	
	@RequestMapping("editUser")
	public String editUser (String id, Model model) {
		User u = usersService.getUserByID(Integer.parseInt(id));
		model.addAttribute("user", u);
		
		return "admin/editUser";
	}
	
	@RequestMapping("saveChangesUser")
	public String saveChangesUser(@ModelAttribute("user") @Valid User user, BindingResult validationsResults, Model model) {
		if (validationsResults.hasErrors()) {
			return "admin/editUser";
		}
		usersService.updateUser(user);
		return getUsers(model);
	}
	
	@RequestMapping("registerUser")
	public String registerUser(Model model) {
		User u = new User();
		model.addAttribute("newUser", u);
		
		return "admin/registerUser";
		
	}
	
	@RequestMapping("saveUser")
	public String saveUser(@ModelAttribute("newUser") @Valid User newUser, BindingResult validationsResults, Model model) {
		if (validationsResults.hasErrors()) {
			return "admin/registerUser";
		}
		
		usersService.createUser(newUser);
		return "admin/registerUserOk";
	}

}
