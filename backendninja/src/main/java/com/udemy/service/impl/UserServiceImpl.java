package com.udemy.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udemy.entity.UserEntity;
import com.udemy.entity.UserRoleEntity;
import com.udemy.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(userEntity.getUserRole());
		
		return buildUser(userEntity, authorities);
	}
	
	private User buildUser(UserEntity user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), 
						user.getPassword(), 
						user.isEnabled(), 
						true, 
						true, 
						true, 
						authorities);
	}
	
	//Objeto GrantedAuthority eh usado pelo Spring para saber as Roles do usuario autenticado
	private List<GrantedAuthority> buildAuthorities(Set<UserRoleEntity> userRoles) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		//Para cada Role existente na lista de userRoles, criar um SimpleGrantedAuthority na lista authorities
		userRoles.forEach(ur -> authorities.add(new SimpleGrantedAuthority(ur.getRole())));
		
		return new ArrayList<>(authorities);
	}
}
