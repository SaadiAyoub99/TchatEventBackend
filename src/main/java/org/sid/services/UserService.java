package org.sid.services;


import org.sid.entities.IUser;
import org.sid.entities.Role;
import org.sid.repositories.RoleRepository;
import org.sid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public IUser saveUser(IUser iUser) {
		return userRepository.save(iUser);
	}
	
	
	public void initRoleUsers() {
		
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Role created for Admins");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Role created for Users");
		roleRepository.save(userRole);
		
		IUser admin = new IUser();
		admin.setUserFirstName("administrateur");
		admin.setUserLastName("Tchat");
		admin.setUserName("admin@tchat.it");
		admin.setUserPassword(getEncoderPassword("@1234"));
		admin.setRole(adminRole);
		userRepository.save(admin);
		

		IUser user1 = new IUser();
		user1.setUserFirstName("Saadi");
		user1.setUserLastName("Ayoub");
		user1.setUserName("saadi@gmail.com");
		user1.setUserPassword(getEncoderPassword("@1234"));
		user1.setRole(userRole);
		userRepository.save(user1);
		
		IUser user2 = new IUser();
		user2.setUserFirstName("Tahiri");
		user2.setUserLastName("Mohammed");
		user2.setUserName("Tahiri@gmail.com");
		user2.setUserPassword(getEncoderPassword("@1234"));
		user2.setRole(userRole);
		userRepository.save(user2);
		
		
	}
	
	
	public String getEncoderPassword(String password) {
		return passwordEncoder.encode(password);
	}


}
