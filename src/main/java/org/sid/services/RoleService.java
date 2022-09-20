package org.sid.services;

import org.sid.entities.Role;
import org.sid.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	public RoleRepository roleRepository;
	
	public Role createRole(Role appRole) {
		return roleRepository.save(appRole);
	}
	
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
}
