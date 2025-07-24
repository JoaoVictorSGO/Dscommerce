package com.joaovictor.commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaovictor.commerce.dto.UserDTO;
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
	
	/*
	 * Recupera o usuário autenticado com base no token JWT presente no contexto de segurança.
	 * O método extrai o username do token JWT e realiza uma busca no banco de dados
	 * para retornar a entidade User correspondente.
	 */
	protected User authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return repository.findByEmail(username).get();
		} catch (Exception e) {
			throw new UsernameNotFoundException("Email not found");
		}
		
	}
	
	@Transactional(readOnly = true)
	public UserDTO getMe() {
		return new UserDTO(authenticated());
	}
	
}
