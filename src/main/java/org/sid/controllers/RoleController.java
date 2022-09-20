package org.sid.controllers;

import org.sid.entities.Role;
import org.sid.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@PostMapping("/createRole")
	public Role createRole(@RequestBody Role role) {
		return roleService.createRole(role);
	}
	
	/*@DeleteMapping("/deleteRole")
	public void deleteRole(@RequestParam Long id) {
		roleService.deleteRole(id);
	}*/
}
