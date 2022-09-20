package org.sid.services;

import java.util.HashSet;
import java.util.Set;

import org.sid.entities.IUser;
import org.sid.entities.JwtRequest;
import org.sid.entities.JwtResponse;
import org.sid.repositories.UserRepository;
import org.sid.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements UserDetailsService{

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final 	AuthenticationManager authenticationManager;
	
	
	@Autowired
	public JwtService(@Lazy UserRepository userRepository,@Lazy JwtUtil jwtUtil,@Lazy AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);
		final UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		IUser iUser = userRepository.findByUserName(userName);
		return new JwtResponse(iUser, newGeneratedToken);
		
	}
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		IUser iUser = userRepository.findByUserName(username);
		
		if(iUser != null) {
			return new User(
					iUser.getUserName(),
					iUser.getUserPassword(),
					getAuthorities(iUser));
						
		} else {
			throw new UsernameNotFoundException("L'utilisateur n'est pas valide");
		}
	}
	
	private Set getAuthorities(IUser iUser) {
		Set authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+ iUser.getRole().getRoleName()));
		return authorities;
}
	
	private void authenticate(String userName, String userPassword) throws Exception{
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
			
		}catch (DisabledException e) {
			throw new Exception("l'utilisateur est désactivé");
		}catch (BadCredentialsException e) {
			throw new Exception("mauvaises informations d'identification de l'utilisateur");
		}
	}

}
