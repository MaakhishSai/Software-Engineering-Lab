package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	// creating method handler for home page 
	// (index.html) to display list of employees
	@GetMapping("/") 
	public  String viewHomePage(Model model) {
		model.addAttribute("listUsers", userService.showUserList());
		return "index";
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}
	
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		// save employee to data base
		if (user.getName().isEmpty() || user.getEmail().isEmpty()) {
	        model.addAttribute("errorMessage", "Name and Email cannot be empty.");
	        return "error_file"; // Redirects to an error page
	    }
		userService.saveUser(user);
		return "redirect:/";
	}
	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		
		// get Employee from the service
		User user = userService.getUserById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "update_user";
	}
	
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id") long id) {
		
		// call delete employee method
		this.userService.deleteUserById(id);
		return "redirect:/";
	}
}
