package com.joaovictor.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joaovictor.commerce.entities.Role;
import com.joaovictor.commerce.entities.User;
import com.joaovictor.commerce.projections.UserDetailsProjection;
import com.joaovictor.commerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetailsProjection> result = repository.loadUserByUsername(username);
		if(result.size() == 0) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		User user = new User();
		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());
		for(UserDetailsProjection userProjetion : result) {
			user.addRole(new Role(userProjetion.getRoleId(),userProjetion.getAuthority()));
		}
		return user;
	}

}
