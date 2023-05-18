package com.techwl.stn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techwl.AppConstants;
import com.techwl.stn.dao.User;
import com.techwl.stn.repositories.UserRepository;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	public String homePage() {
		return "Welcome";
	}	
	
	@GetMapping("logout")
	public String logout() {
		return "Welcome";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("name")String name, @RequestParam("pwd")String password) {		
		User validateUser = userRepository.validateUser(name, password);		
		return validateUser != null ? AppConstants.dashboard.toString(): AppConstants.errorLogin.toString();
	}

	@GetMapping("/dashboard")
	public String showDashboard() {
		return AppConstants.dashboard.toString();
	}

	
	
}
