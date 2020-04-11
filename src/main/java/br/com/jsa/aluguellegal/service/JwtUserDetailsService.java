package br.com.jsa.aluguellegal.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		if ("springuser".equals(username)) {
			return new User("springuser", "$2a$10$z8IG6lrBxmVDDo/560sTHOHSkUvI3ppuCbeqQ29FjkhldsRUjEvIW",
					new ArrayList<>());
		} else {
			System.out.println("SENHA INCORRETA");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	
}
