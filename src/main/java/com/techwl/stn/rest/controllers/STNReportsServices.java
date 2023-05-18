package com.techwl.stn.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techwl.stn.dao.User;
import com.techwl.stn.repositories.UserRepository;
import com.techwl.stn.views.beans.UserBean;

@RestController
public class STNReportsServices {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/restReports")
	public String getReport(@RequestParam("startDate") String startDate) {
		return "No data to genereate reportes";
	}
	
	
	@PostMapping("/register")
	public String registerUser(UserBean user) {
		
		User userDetails = new User();
		userDetails.setActive(Boolean.TRUE);
		userDetails.setEmail(user.getEmailId());
		userDetails.setMobile(user.getMobileNo());
		userDetails.setName(user.getName());		
		
		userRepository.save(userDetails);		
		return "success";
	}
}
