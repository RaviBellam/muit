package com.techwl.stn.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techwl.stn.dao.User;
import com.techwl.stn.repositories.UserRepository;
import com.techwl.stn.views.beans.UserBean;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody UserBean userbean) {
		User user = new User();
		user.setActive(Boolean.TRUE);
		user.setEmail(userbean.getEmailId());
		user.setName(userbean.getName());
		user.setPassword(generateTempPassword());
		user.setMobile(userbean.getMobileNo());
		user.setCreatedDate(new Date());
		user.setId(userRepository.count() + 1);
		userRepository.save(user);
		
		return user;
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers() {		
		List<User> usersList = userRepository.findAll();
		usersList.stream().forEach(user -> user.setPassword(null));
		return usersList;
	}
	
	
	private String generateTempPassword() {		
		return "TEMP";
	}
	
	
	
}
