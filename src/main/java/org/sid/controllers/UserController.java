package org.sid.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.sid.entities.IUser;
import org.sid.repositories.IUserRepository;
import org.sid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	public UserService userService;
	
	@Autowired
	public IUserRepository iUserRepository;
	
	/*
	@PostConstruct
	public void initRoleUsers() {
		userService.initRoleUsers();
	}
	*/
	
	@GetMapping("/users")
	public List<IUser> getAllUsers() {
		return iUserRepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	public IUser findById(@PathVariable("id") Long id) {
		return iUserRepository.findById(id).get();

	}
	
	@PostMapping("/saveUser")
	public IUser saveUser(@RequestBody IUser iUser) {
		return userService.saveUser(iUser);
	}
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('Admin')")
	public String userDash(){
		return " This url is only for admin ";
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasRole('User')")
	public String adminDash(){
		return " This url is only for user ";
	}
	
	@GetMapping("/forAllUsers")
	@PreAuthorize("hasAnyRole('Admin, User')")
	public String AllDash(){
		return " This url is only for user ";
	}
	
}
