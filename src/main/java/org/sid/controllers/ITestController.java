package org.sid.controllers;

import javax.annotation.PostConstruct;

import org.sid.services.ITestService;
import org.sid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ITestController {

	
	@Autowired
	public ITestService iTestService;
	
	@Autowired
	public UserService userService;
	
	/*
	@PostConstruct
	public void initRoleUsers() {
		userService.initRoleUsers();
	}
	
	@PostConstruct
	public void initVille() {
		iTestService.initVille();
	}
	
	
	@PostConstruct
	public void initCentre() {
		iTestService.initCentre();
	}
	
	
	@PostConstruct
	public void initSalle() {
		iTestService.initSalle();
	}
	
	
	@PostConstruct
	public void initPlace() {
		iTestService.initPlace();
	}
	
	
	@PostConstruct
	public void initCategorie() {
		iTestService.initCategorie();
	}
	*/
	
	
}
